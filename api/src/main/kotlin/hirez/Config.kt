package hirez

import hirez.api.BaseEndpoint
import hirez.api.SessionStorage

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 3.0.0
 */
data class Config internal constructor(
			val devId: String,
			val authKey: String,
			val defaultLanguage: Language,
			val sessionStorage: SessionStorage,
			val userAgent: String,
			val endpoint: BaseEndpoint
)