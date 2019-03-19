package hirez.api

import hirez.Config
import hirez.exceptions.SessionException
import hirez.flattenArray
import hirez.format
import io.reactivex.Single
import java.security.NoSuchAlgorithmException
import java.util.*

/**
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 3.0.0
 */

abstract class AbstractAPI protected constructor() {
	protected lateinit var config: Config
	protected lateinit var http: Http
	
	@Suppress("UNCHECKED_CAST")
	protected inline operator fun <reified T : Any> get(endpoint: String, vararg params: String) =
				Single.create<T> { sink ->
					if (!isInitialized) {
						sink.onError(ExceptionInInitializerError("configuration is not initialized"))
					} else {
						http.call<T>(doURL(doSuffix(endpoint, *params)))
					}
				}.flatMap { res ->
					Single.create<T> { sink ->
						val r = if (res is Array<*>) res[0] else res
						if (r is ReturnedMessage) {
							if (r.returnedMessage != null || !(endpoint == "createsession" && r.returnedMessage == "Approved")) {
								sink.onError(SessionException(Objects.requireNonNull<String>(r.returnedMessage)))
							} else {
								sink.onSuccess(res)
							}
						} else
							sink.onSuccess(res)
					}
				}
	
	protected inline fun <reified T : Any> getCollection(endpoint: String, vararg params: String) =
				get<Array<T>>(endpoint, *params)
	
	protected inline fun <reified T : Any> getCollectionFirst(endpoint: String, vararg params: String) =
				getCollection<T>(endpoint, *params).map { it[0] }
	
	protected inline fun <reified T : Any> getFlattenIterable(endpoint: String, vararg params: String) =
				getCollection<T>(endpoint, *params).flattenArray()
	
	protected fun <T : Any> getAndMap(endpoint: String, vararg params: String, mapper: (String) -> T) =
				get<String>(endpoint, *params).map(mapper)
	
	@PublishedApi
	internal fun doURL(endpoint: String): String {
		return String.format("%s/%s", config.endpoint.baseUrl, endpoint)
	}
	
	@PublishedApi
	@Throws(SessionException::class, NoSuchAlgorithmException::class)
	internal fun doSuffix(endpoint: String, vararg params: String): String {
		val ts = Calendar.getInstance().format("yyyyMMddHHmmss")
		val sig = "${config.devId}$endpoint${config.authKey}$ts".md5()
		
		return when (endpoint.toLowerCase()) {
			"createsession" -> "${endpoint}json/${config.devId}/$sig/$ts"
			"ping" -> "${endpoint}json"
			else -> if (config.sessionStorage.isPresent) {
				"${endpoint}json/${config.devId}/$sig/${config.sessionStorage.get()}/$ts${if (params.isNotEmpty()) params.joinToString("/", "/") else ""}"
			} else {
				throw SessionException("There is no session created for this endpoint: $endpoint")
			}
		}
	}
	
	@PublishedApi
	internal val isInitialized: Boolean
		get() = ::config.isInitialized && ::http.isInitialized
}
