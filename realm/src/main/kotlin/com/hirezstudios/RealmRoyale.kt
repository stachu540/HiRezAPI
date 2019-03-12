package com.hirezstudios

import hirez.AbstractAPI
import hirez.Configuration
import hirez.enums.Language
import hirez.BaseEndpoint
import hirez.api.session.DefaultSessionStorage
import hirez.SessionStorage
import com.hirezstudios.smitegame.json.RealmPlayer

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
class RealmRoyale(conf: Configuration, storage: SessionStorage) : AbstractAPI<RealmPlayer>(conf, storage) {

    companion object {
        @JvmStatic
        fun builder() = Builder()

        fun create(builder: Builder.() -> Unit) = builder().apply(builder).build()
    }

    class Builder internal constructor() {
        lateinit var devId: String
        lateinit var authKey: String
        var sessionStorage: SessionStorage = DefaultSessionStorage()
        var defaultLanguage = Language.English

        fun build(): RealmRoyale {
            if (!::devId.isInitialized || devId.isBlank()) {
                throw NullPointerException("devId must be initialized")
            }
            if (!::authKey.isInitialized || authKey.isBlank()) {
                throw NullPointerException("authKey must be initialized")
            }

            return RealmRoyale(
                  Configuration(object : BaseEndpoint {
                      override val game = "Realm"
                      override val platform = "PC"
                      override val baseUrl = "http://api.realmroyale.com/realmapi.svc"
                  }, devId, authKey, defaultLanguage),
                sessionStorage
            )
        }
    }
}