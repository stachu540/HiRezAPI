package hirez.paladins

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import hirez.GitProperties
import hirez.AbstractAPI
import hirez.Configuration
import hirez.enums.Language
import hirez.BaseEndpoint
import hirez.Game
import hirez.Queue
import hirez.SessionStorage
import hirez.enums.Portal
import hirez.exceptions.PlayerNotFoundException
import hirez.paladins.json.PaladinsPlayer
import io.reactivex.Single

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
class Paladins(
			configuration: Configuration,
			session: SessionStorage,
			gson: GsonBuilder,
			userAgent: String
) : AbstractAPI<PaladinsPlayer>(configuration, session, gson, userAgent) {
	
	override fun getPlayer(name: String) = get<Array<PaladinsPlayer>>("getplayer", name).flatMap { p ->
		Single.create<PaladinsPlayer> {
			if (p.isNotEmpty()) {
				it.onSuccess(p[0])
			} else {
				it.onError(PlayerNotFoundException("Player is not exist or it is hidden"))
			}
		}
	}
	
	override fun getPlayer(name: String, portal: Portal) = get<Array<PaladinsPlayer>>("getplayer", toDefaultPortal(portal), name).flatMap { p ->
		Single.create<PaladinsPlayer> {
			if (p.isNotEmpty()) {
				it.onSuccess(p[0])
			} else {
				it.onError(PlayerNotFoundException("Player is not exist or it is hidden"))
			}
		}
	}
	
	val champions
		get() = getChampions(configuration.defaultLanguage)
	
	fun getChampions(language: Language) {}
//        get<Array<Champion>>("getchampions", language.id).flattenAsFlowable { it }
	
	val championCards
		get() = getChampionCards(configuration.defaultLanguage)
	
	fun getChampionCards(language: Language) {}
	
	fun getChampionLeaderboard(championId: Long, queue: Queue) {}
	
	fun getChampionSkins(championId: Long) = getChampionSkins(championId, configuration.defaultLanguage)
	fun getChampionSkins(championId: Long, language: Language) {}
	
	fun getChampionRanks(playerId: Long) {}
	
	fun getPlayerLoadouts(playerId: Long) = getPlayerLoadouts(playerId, configuration.defaultLanguage)
	fun getPlayerLoadouts(playerId: Long, language: Language) {}
	
	fun getPlayerIdInfoForXboxAndSwitch(gamerTag: String) {}
	
	companion object {
		@JvmStatic
		fun builder() = Builder()
		
		fun create(builder: Builder.() -> Unit) = builder().apply(builder).build()
	}
	
	class Builder internal constructor() {
		var platform: Platform = Platform.PC
			private set
		lateinit var devId: String
			private set
		lateinit var authKey: String
			private set
		var defaultLanguage: Language = Language.English
			private set
		var sessionStorage: SessionStorage = SessionStorage.DEFAULT
			private set
		var userAgent: String = "HiRez-API v${GitProperties.get(GitProperties.APPLICATION_VERSION)} [Rev. ${GitProperties.get(GitProperties.GIT_COMMIT_ID_ABBREV)}]"
			private set
		
		fun setPlatform(platform: Platform): Builder {
			this.platform = platform
			return this
		}
		
		fun setDevId(devId: String): Builder {
			this.devId = devId
			return this
		}
		
		fun setAuthKey(authKey: String): Builder {
			this.authKey = authKey
			return this
		}
		
		fun setDefaultLanguage(defaultLanguage: Language): Builder {
			this.defaultLanguage = defaultLanguage
			return this
		}
		
		fun setSessionStorage(sessionStorage: SessionStorage): Builder {
			this.sessionStorage = sessionStorage
			return this
		}
		
		fun setUserAgent(userAgent: String): Builder {
			this.userAgent = userAgent
			return this
		}
		
		fun build(): Paladins {
			if (!::devId.isInitialized || devId.isBlank()) {
				throw NullPointerException("devId must be initialized")
			}
			if (!::authKey.isInitialized || authKey.isBlank()) {
				throw NullPointerException("authKey must be initialized")
			}
			
			return Paladins(Configuration(platform, devId, authKey, defaultLanguage),
						sessionStorage, GsonBuilder(), userAgent)
		}
	}
	
	enum class Platform(override val baseUrl: String, platform: String) : BaseEndpoint {
		PC("http://api.paladins.com/paladinsapi.svc", "8xmmtyh24dvk"),
		XBOX("http://api.xbox.paladins.com/paladinsapi.svc", "z44md5h2qg1f"),
		PS4("http://api.ps4.paladins.com/paladinsapi.svc", "m80484kp0zhn");
		
		override val game = Game("542zlqj9nwr6", "Paladins")
		
		override val platform = hirez.Platform(platform, name)
	}
}