package hirez.realm.json

import com.google.gson.annotations.SerializedName
import hirez.enums.Portal
import hirez.json.IdObject
import hirez.json.ReturnMessage
import java.util.*


/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class Player(
			@SerializedName("created_datetime")
			val createdAt: Date,
			override val id: Long,
			@SerializedName("last_login_datetime")
			val lastLogin: Date,
			val level: Int,
			val name: String,
			@SerializedName("portal")
			val portalName: String,
			@SerializedName("portal_id")
			val portalId: Int,
			@SerializedName("portal_userid")
			val portalUserid: Long,
			val region: String,
			val steamId: Long,
			@SerializedName("ret_msg")
			override val returnMessage: String?
) : ReturnMessage, IdObject<Long> {
	val portal: Portal = Portal.values().firstOrNull { it.id == portalId } ?: Portal.UNKNOWN
}


data class Stats(
			val assists: Int,
			val averagePlacement: Int,
			val damageDoneInHand: Int,
			val damageMitigated: Int,
			val damagePlayer: Int,
			val damageTaken: Int,
			val deaths: Int,
			val durationSecs: Int,
			val earnedTokens: Int,
			val earnedXp: Int,
			val gamesPlayed: Int,
			val healingPlayer: Float,
			val healingPlayerSelf: Int,
			val killingSpreeMax: Int,
			val killsBot: Int,
			val killsPlayer: Int,
			val losses: Int,
			val placements: Map<Int, Int>,
			val wardsMinesPlaced: Int,
			val wins: Int
)

data class PlayerStat(
			@SerializedName("aggregate_stats")
			val aggregateStats: Stats,
			override val id: Long,
			@SerializedName("queue_class_stats")
			val queueClassStats: List<QueueStat>,
			@SerializedName("ret_msg")
			override val returnMessage: String?
) : IdObject<Long>, ReturnMessage

data class QueueStat(
			val classId: Int,
			val className: String,
			val matchQueueId: Int,
			val matchQueueName: String,
			val stats: Stats
)

data class MatchHistory(
			override val id: Long,
			val name: String,
			@SerializedName("ret_msg")
			override val returnMessage: String?,
			val matches: List<MatchHistory.Item>
) : IdObject<Long>, ReturnMessage {
	data class Item(
				val assists: Int,
				val classId: Long,
				val className: String,
				val creeps: Int,
				val damage: Int,
				val damageDoneInHand: Int,
				val damageMitigated: Int,
				val damageTaken: Int,
				val deaths: Int,
				val gold: Int,
				val healingBot: Int,
				val healingPlayer: Int,
				val healingPlayerSelf: Int,
				val killingSpreeMax: Int,
				val kills: Int,
				val mapGame: String,
				@SerializedName("match_datetime")
				val createdAt: Date,
				val matchDurationSecs: Long,
				val matchId: Long,
				val matchQueueId: Long,
				val matchQueueName: String,
				val placement: Int,
				val playerId: Long,
				val region: String,
				val teamId: Long,
				val timeInMatchMinutes: Long,
				val timeInMatchSecs: Long,
				val wardsMinesPlaced: Int
	)
}

data class PlayerItem(
    override val id: Long,
    val name: String,
    @SerializedName("ret_msg")
    override val returnMessage: String?,
    @SerializedName("steam_id")
    val steamId: Long
): ReturnMessage, IdObject<Long>