package hirez.smite.json

import com.google.gson.annotations.SerializedName
import hirez.json.IdObject
import hirez.json.MergedPlayer
import hirez.json.RankedItem
import hirez.json.ReturnMessage
import java.util.*


/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class Player(
			@SerializedName("ret_msg")
			override val returnMessage: String?,
			override val id: Long,
			val leaves: Long,
			val losses: Long,
			val name: String,
			val wins: Long,
			val activePlayerId: Long,
			val mergedPlayers: List<MergedPlayer>?,
			@SerializedName("Avatar_URL")
			val avatar: String,
			val hoursPlayed: Int,
			@SerializedName("Created_Datetime")
			val createdAt: Date,
			@SerializedName("Last_Login_Datetime")
			val lastLogin: Date,
			val level: Int,
			val masteryLevel: Int,
			@SerializedName("Personal_Status_Message")
			val statusMessage: String?,
			val region: String,
			val teamId: Int,
			val teamName: String,
			val totalAchievements: Int,
			val totalWorshippers: Int,
			@SerializedName("hz_gamer_tag")
			val hiRezGamerTag: String?,
			@SerializedName("hz_player_name")
			val hiRezPlayerName: String,
			val rankedConquest: RankedItem,
			val rankedDuel: RankedItem,
			val rankedJoust: RankedItem,
			val rankedConquestController: RankedItem,
			val rankedDuelController: RankedItem,
			val rankedJoustController: RankedItem
) : ReturnMessage, IdObject<Long> {
	val tierConquest = rankedConquest.tier
	val tierDuel = rankedDuel.tier
	val tierJoust = rankedJoust.tier
}

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class GodRank(
			@SerializedName("Assists")
			val assists: Int,
			@SerializedName("Deaths")
			val deaths: Int,
			@SerializedName("Kills")
			val kills: Int,
			@SerializedName("Losses")
			val losses: Int,
			@SerializedName("MinionKills")
			val minionKills: Int,
			@SerializedName("Rank")
			val rank: Int,
			@SerializedName("Wins")
			val wins: Int,
			@SerializedName("Worshippers")
			val worshippers: Int,
			val god: String,
			@SerializedName("god_id")
			val godId: Long,
			@SerializedName("player_id")
			val playerId: Long,
			override val returnMessage: String?
) : ReturnMessage
