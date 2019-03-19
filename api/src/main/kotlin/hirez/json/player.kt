package hirez.json

import com.google.gson.annotations.SerializedName
import hirez.enums.Portal
import java.util.*

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class MergedPlayer(
			val mergeDatetime: Date,
			val playerId: String,
			val portalId: String
)

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class PlayerState(
			@SerializedName("match", alternate = ["match_id"])
			val match: Int?,
			val personalStatusMessage: String?,
			@SerializedName("status_id", alternate = ["status"])
			val status: Status,
			@SerializedName("status_string", alternate = ["status"])
			val statusString: String,
			override val returnMessage: String?
): ReturnMessage {
	enum class Status { OFFLINE, IN_LOBBY, GOD_SELECTION, IN_GAME, ONLINE, UNKNOWN }
}

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class PlayerData(
			@SerializedName("ret_msg")
			override val returnMessage: String,
			@SerializedName("player_id")
			override val id: Long,
			@SerializedName("portal")
			val portalName: String,
			@SerializedName("portal_id")
			val portalId: Int
) : IdObject<Long>, ReturnMessage {
	val portal: Portal = Portal.values().firstOrNull { it.id == portalId } ?: Portal.UNKNOWN
}

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class Friend(
			@SerializedName("ret_msg")
			override val returnMessage: String,
			val accountId: Long,
			val avatarUrl: String,
			val name: String,
			val playerId: Long
) : ReturnMessage