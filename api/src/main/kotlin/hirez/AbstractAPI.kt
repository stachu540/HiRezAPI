package hirez

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import hirez.enums.Portal
import hirez.exceptions.SessionException
import hirez.json.CreateSession
import hirez.json.DataUsage
import hirez.json.Friend
import hirez.json.Item
import hirez.json.Ping
import hirez.json.Player
import hirez.json.PlayerData
import hirez.json.RankedItem
import hirez.json.ReturnMessage
import hirez.json.Server
import hirez.json.TestSession
import hirez.json.adapters.DateFormatAdapter
import hirez.json.adapters.RankedTierAdapter
import io.reactivex.Flowable
import io.reactivex.Single
import java.text.SimpleDateFormat
import java.time.ZoneOffset
import java.util.*

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
abstract class AbstractAPI<P : Player> protected constructor(
			val configuration: Configuration,
			val session: SessionStorage,
			gson: GsonBuilder = GsonBuilder(),
			userAgent: String
) {
	fun ping() = get<String>("ping").map { Ping(it) }
	
	fun createSession() = get<CreateSession>("createsession")
				.doOnSuccess(session::set)
	
	fun testSession() =
				get<String>("testsession")
							.map { TestSession(it) }
							.doOnSuccess { if (!it.isSuccessful) session.remove() }
	
	val dataUsage
		get() = get<Array<DataUsage>>("getdataused").map { it[0] }
	
	abstract fun getPlayer(name: String): Single<P>
	fun getPlayer(id: Long) = getPlayer(id.toString())
	
	abstract fun getPlayer(name: String, portal: Portal): Single<P>
	fun getPlayer(id: Long, portal: Portal) = getPlayer(id.toString(), portal)
	
	@PublishedApi
	internal val http = Http(gson.apply {
		serializeNulls()
		setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
		registerTypeAdapter(Date::class.java, DateFormatAdapter())
		registerTypeAdapter(RankedItem.Tier::class.java, RankedTierAdapter())
	}.create(), userAgent)
	
	protected inline fun <reified T : Any> get(endpoint: String, vararg params: String) =
				doSuffix(endpoint, *params).flatMap {
					http.call<T>(doURL(it))
				}.flatMap {
					Single.create<T> { sink ->
						if (it is ReturnMessage) {
							if (it.returnMessage != null || !(endpoint == "createsession" && it.returnMessage == "Approved"))
								sink.onError(SessionException(it.returnMessage!!))
							else sink.onSuccess(it)
						}	else sink.onSuccess(it)
					}
				}
	
	@PublishedApi
	internal fun doURL(endpoint: String) = "${configuration.baseEndpoint.baseUrl}/$endpoint"
	
	@PublishedApi
	internal fun doSuffix(endpoint: String, vararg params: String) = Single.create<String> {
		val ts = SimpleDateFormat("yyyyMMddHHmmss").apply {
			timeZone = TimeZone.getTimeZone(ZoneOffset.UTC)
		}.format(Date())
		val signature = "${configuration.devId}$endpoint${configuration.authKey}$ts".md5()
		when (endpoint.toLowerCase()) {
			"createsession" -> it.onSuccess("${endpoint}json/${configuration.devId}/$signature/$ts")
			"ping" -> it.onSuccess("${endpoint}json")
			else -> {
				if (session.isPresent) {
					it.onSuccess(
								"${endpoint}json/${configuration.devId}/$signature/${session.get()!!}/$ts" +
											if (params.isNotEmpty()) params.joinToString("/", "/") else ""
					)
				} else {
					it.onError(SessionException("There is no session created for this endpoint: $endpoint"))
				}
			}
		}
	}
	
	val serverStatus
		get() = get<Array<Server>>("gethirezserverstatus").flattenAsFlowable { it.asIterable() }
	val patchInfo
		get() = get<JsonObject>("getpatchinfo").map { it.getAsJsonPrimitive("version_string").asDouble }
	val items
		get() = get<Array<Item>>("getitems").flattenAsFlowable { it.asIterable() }
	
	fun getFriends(name: String) =
				if (configuration.baseEndpoint.platform.name.toUpperCase() == "PC")
					get<Array<Friend>>("getfriends", name).flattenAsFlowable { it.asIterable() }
				else Flowable.error(IllegalAccessException("Only PC platform is supported"))
	
	fun getFriends(id: Long) = getFriends(id.toString())
	
	fun getPlayerIdByName(name: String) =
				get<Array<PlayerData>>("getplayeridbyname", name).map { it.first() }
	
	fun getPlayerIdByPortalUserId(portal: Portal, id: String) =
				get<Array<PlayerData>>("getplayeridbyportaluserid", toDefaultPortal(portal), id).map { it.first() }
	
	fun getPlayerIdsByGamerTag(portal: Portal, name: String) =
				get<Array<PlayerData>>("getplayeridsbygamertag", toDefaultPortal(portal), name).map { it.first() }
	
	protected fun toDefaultPortal(portal: Portal) = (if (portal == Portal.UNKNOWN) Portal.HIREZ else portal).id.toString()
	
}