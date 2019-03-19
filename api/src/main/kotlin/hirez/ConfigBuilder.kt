package hirez

import hirez.api.BaseEndpoint
import hirez.api.SessionStorage

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 3.0.0
 */
class ConfigBuilder {
	lateinit var devId: String
		private set
	lateinit var authKey: String
		private set
	lateinit var endpoint: BaseEndpoint
		private set
	var sessionStorage: SessionStorage = SessionStorage.DEFAULT
		private set
	var userAgent: String = "HiRez-API v${GitProperties.get(GitProperties.APPLICATION_VERSION)} [Rev. ${GitProperties.get(GitProperties.GIT_COMMIT_ID_ABBREV)}]"
		private set
	var defaultLanguage: Language = Language.English
		private set
	
	fun setDevId(devId: String): ConfigBuilder {
		this.devId = devId
		return this
	}
	
	fun setAuthKey(authKey: String): ConfigBuilder {
		this.authKey = authKey
		return this
	}
	
	fun setEndpoint(endpoint: BaseEndpoint): ConfigBuilder {
		this.endpoint = endpoint
		return this
	}
	
	fun setSessionStorage(sessionStorage: SessionStorage): ConfigBuilder {
		this.sessionStorage = sessionStorage
		return this
	}
	
	fun setUserAgent(userAgent: String): ConfigBuilder {
		this.userAgent = userAgent
		return this
	}
	
	fun setDefaultLanguage(defaultLanguage: Language): ConfigBuilder {
		this.defaultLanguage = defaultLanguage
		return this
	}
	
	val endpointAssigned
		get() = ::endpoint.isInitialized
	
	fun build(): Config {
		if (!::devId.isInitialized || devId.isBlank()) {
			throw NullPointerException("devId must be initialized")
		}
		if (!::authKey.isInitialized || authKey.isBlank()) {
			throw NullPointerException("authKey must be initialized")
		}
		if (!::endpoint.isInitialized) {
			throw NullPointerException("endpoint must be initialized")
		}
		
		return Config(devId, authKey, defaultLanguage, sessionStorage, userAgent, endpoint)
	}
}