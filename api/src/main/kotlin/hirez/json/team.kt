package hirez.json

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class TeamMember(
			@SerializedName("ret_msg")
			override val returnMessage: String?,
			@SerializedName("AccountLevel")
			val level: Int,
			@SerializedName("JoinedDatetime")
			val createdAt: Date,
			@SerializedName("LastLoginDatetime")
			val updatedAt: Date,
			@SerializedName("Name")
			val name: String
) : ReturnMessage

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class Team(
			@SerializedName("ret_msg")
			override val returnMessage: String,
			val founder: String,
			val founderId: Long,
			val losses: Int,
			val name: String,
			val players: Int,
			val rating: Int,
			val tag: String,
			val teamId: Long,
			val wins: Int
) : ReturnMessage