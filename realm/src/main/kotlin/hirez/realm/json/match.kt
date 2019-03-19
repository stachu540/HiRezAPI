package hirez.realm.json

import com.google.gson.annotations.SerializedName
import hirez.api.Queue
import hirez.json.IdObject
import hirez.json.ReturnMessage
import hirez.realm.Queues
import java.util.*

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class QueueLeaderboard(
			@SerializedName("ret_msg")
			override val returnMessage: String?,
			@SerializedName("queue_id")
			override val id: Int,
			@SerializedName("queue")
			val name: String,
			@SerializedName("last_updated")
			val updatedAt: Date,
			val leaderboard: QueueLeaderboard.Item
) : IdObject<Int>, ReturnMessage {
	val queue: Queue = Queues.from(id)
	
	data class Item(
				val rank: Int,
				val playerName: String,
				val playerId: String,
				val matches: Int,
				@SerializedName("team_wins")
				val wins: Int,
				@SerializedName("team_avg_placement")
				val averagePlacement: Double
	)
}

data class Match(
			@SerializedName("ret_msg")
			override val returnMessage: String?,
			override val id: Long,
			val region: String,
			val queueId: Int,
			@SerializedName("queue")
			val queueName: String,
			@SerializedName("duration_secs")
			val duration: Long,
			val teams: List<Match.Team>
) : IdObject<Long>, ReturnMessage {
	data class Team(
				override val id: Long,
				val placement: Int,
				val players: List<Match.Team.Player>
	) : IdObject<Long> {
		
		data class Player(
					val assists: Int,
					val classId: Int,
					val className: String,
					val damageDoneInHand: Int,
					val damageMitigated: Int,
					val damagePlayer: Int,
					val damageTaken: Int,
					val deaths: Int,
					val droppedOutFlag: Int,
					val durationSecs: Int,
					val earnedTokens: Int,
					val earnedXp: Int,
					val healingPlayer: Double,
					val healingPlayerSelf: Int,
					val id: Int,
					val killingSpreeMax: Int,
					val killsBot: Int,
					val killsPlayer: Int,
					val level: Int,
					val minesWardsPlaced: Int,
					val name: String
		)
	}
}