package hirez

import hirez.json.IdObject

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
interface Queue {
	val id: Int
	val value: String
	val isRanked: Boolean
}

interface BaseEndpoint {
	val game: Game
	
	val platform: Platform
	
	val baseUrl: String
}

data class Game(override val id: String, val name: String) : IdObject<String>
data class Platform(override val id: String, val name: String) : IdObject<String>