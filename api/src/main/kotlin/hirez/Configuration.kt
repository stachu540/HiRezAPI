package hirez

import hirez.enums.Language

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class Configuration(
			var baseEndpoint: BaseEndpoint,
			val devId: String,
			val authKey: String,
			var defaultLanguage: Language = Language.English
) {
	constructor(
				baseEndpoint: BaseEndpoint,
				devId: String,
				authKey: String
	) : this(baseEndpoint, devId, authKey, Language.English)
}