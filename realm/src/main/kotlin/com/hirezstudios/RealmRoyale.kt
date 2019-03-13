package com.hirezstudios

import com.google.gson.GsonBuilder
import com.hirezstudios.smitegame.json.RealmPlayer
import hirez.AbstractAPI
import hirez.BaseEndpoint
import hirez.Configuration
import hirez.Game
import hirez.GitProperties
import hirez.Platform
import hirez.SessionStorage
import hirez.enums.Language
import hirez.enums.Portal
import hirez.exceptions.PlayerNotFoundException
import io.reactivex.Single

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
class RealmRoyale(
			configuration: Configuration,
			session: SessionStorage,
			gson: GsonBuilder = GsonBuilder(),
			userAgent: String
) : AbstractAPI<RealmPlayer>(configuration, session, gson, userAgent) {
	override fun getPlayer(name: String) = get<Array<RealmPlayer>>("getplayer", name).flatMap { p ->
		Single.create<RealmPlayer> {
			if (p.isNotEmpty()) {
				it.onSuccess(p[0])
			} else {
				it.onError(PlayerNotFoundException("Player is not exist or it is hidden"))
			}
		}
	}
	
	override fun getPlayer(name: String, portal: Portal) = get<Array<RealmPlayer>>("getplayer", toDefaultPortal(portal), name).flatMap { p ->
		Single.create<RealmPlayer> {
			if (p.isNotEmpty()) {
				it.onSuccess(p[0])
			} else {
				it.onError(PlayerNotFoundException("Player is not exist or it is hidden"))
			}
		}
	}
	
	companion object {
		@JvmStatic
		fun builder() = Builder()
		
		fun create(builder: Builder.() -> Unit) = builder().apply(builder).build()
	}
	
	class Builder internal constructor() {
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
		
		fun build(): RealmRoyale {
			if (!::devId.isInitialized || devId.isBlank()) {
				throw NullPointerException("devId must be initialized")
			}
			if (!::authKey.isInitialized || authKey.isBlank()) {
				throw NullPointerException("authKey must be initialized")
			}
			
			return RealmRoyale(Configuration(object : BaseEndpoint {
				override val game: Game = Game("", "Realm Royale")
				override val platform = Platform("", "PC")
				override val baseUrl = "http://api.realmroyale.com/realmapi.svc"
			}, devId, authKey, defaultLanguage),
						sessionStorage, GsonBuilder(), userAgent)
		}
	}
}