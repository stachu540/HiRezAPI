package hirez.api

import com.google.gson.annotations.SerializedName

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

interface ReturnedMessage {
	@get:SerializedName("ret_msg")
	val returnedMessage: String?
}

data class Game(val id: String, val name: String)
data class Platform(val id: String, val name: String)