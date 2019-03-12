package hirez.smite

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import hirez.smite.json.SmitePlayer
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
import hirez.json.Item
import hirez.smite.json.Achievements
import hirez.smite.json.God
import hirez.smite.json.GodRank
import hirez.smite.json.GodSkin
import hirez.smite.json.TeamItemSearch
import hirez.smite.json.UserGodRank
import io.reactivex.Maybe
import io.reactivex.Single

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
class SmiteGame internal constructor(
			configuration: Configuration,
			session: SessionStorage,
			gson: GsonBuilder = GsonBuilder(),
			userAgent: String
) : AbstractAPI<SmitePlayer>(configuration, session, gson, userAgent) {
	
	override fun getPlayer(name: String) = get<Array<SmitePlayer>>("getplayer", name).flatMap { p ->
		Single.create<SmitePlayer> {
			if (p.isNotEmpty()) {
				it.onSuccess(p[0])
			} else {
				it.onError(PlayerNotFoundException("Player is not exist or it is hidden"))
			}
		}
	}
	
	override fun getPlayer(name: String, portal: Portal) = get<Array<SmitePlayer>>("getplayer", toDefaultPortal(portal), name).flatMap { p ->
		Single.create<SmitePlayer> {
			if (p.isNotEmpty()) {
				it.onSuccess(p[0])
			} else {
				it.onError(PlayerNotFoundException("Player is not exist or it is hidden"))
			}
		}
	}
	
	val gods
		get() = getGods(configuration.defaultLanguage)
	
	fun getGods(language: Language) = get<Array<God>>("getgods", language.id.toString()).flattenAsFlowable { it.asIterable() }
	
	fun getGodLeaderboard(godId: Long, queue: Queue) = get<Array<GodRank>>("getgodleaderboard", godId.toString(), queue.id.toString()).flattenAsFlowable { it.asIterable() }
	
	fun getGodSkins(godId: Long) = getGodSkins(godId, configuration.defaultLanguage)
	fun getGodSkins(godId: Long, language: Language) = get<Array<GodSkin>>("getgodskins", godId.toString(), language.id.toString()).flattenAsFlowable { it.asIterable() }
	
	val godRecommendedItems
		get() = getGodRecommendedItems(configuration.defaultLanguage)
	
	fun getGodRecommendedItems(language: Language) = get<Array<Item>>("getgodrecommendeditems", language.id.toString()).flattenAsFlowable { it.asIterable() }
	
	fun getGodRanks(player: String) = get<Array<UserGodRank>>("getgodranks", player).flattenAsFlowable { it.asIterable() }
	
	fun getPlayerAchievements(playerId: Long) = get<Achievements>("getplayerachievements", playerId.toString())
	
	fun searchTeam(query: String) = get<Array<TeamItemSearch>>("searchteams", query).flattenAsFlowable { it.asIterable() }
	
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
		
		fun build(): SmiteGame {
			if (!::devId.isInitialized || devId.isBlank()) {
				throw NullPointerException("devId must be initialized")
			}
			if (!::authKey.isInitialized || authKey.isBlank()) {
				throw NullPointerException("authKey must be initialized")
			}
			
			return SmiteGame(Configuration(platform, devId, authKey, defaultLanguage),
						sessionStorage, GsonBuilder(), userAgent)
		}
	}
	
	enum class Platform(override val baseUrl: String, platform: String) : BaseEndpoint {
		PC("http://api.smitegame.com/smiteapi.svc", "23d1x2hb4kyq"),
		XBOX("http://api.xbox.smitegame.com/smiteapi.svc", "7q3rm3krkkt6"),
		PS4("http://api.ps4.smitegame.com/smiteapi.svc", "glnkmmppldgp");
		
		override val game = Game("542zlqj9nwr6", "Smite")
		
		override val platform = hirez.Platform(platform, name)
	}
}