package hirez.smite.json

import com.google.gson.annotations.SerializedName
import hirez.json.IdObject
import hirez.json.MergedPlayer
import hirez.json.Player
import hirez.json.RankedItem
import hirez.json.ReturnMessage
import java.util.*


/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class SmitePlayer(
			override val id: Long,
			override val leaves: Long,
			override val losses: Long,
			override val name: String,
			override val wins: Long,
			override val activePlayerId: Long,
			override val mergedPlayers: List<MergedPlayer>?,
			override val avatar: String,
			override val hoursPlayed: Int,
			override val createdAt: Date,
			override val lastLogin: Date,
			override val level: Int,
			override val masteryLevel: Int,
			override val statusMessage: String?,
			override val region: String,
			override val teamId: Int,
			override val teamName: String,
			override val totalAchievements: Int,
			override val totalWorshippers: Int,
			override val hiRezGamerTag: String?,
			override val hiRezPlayerName: String,
			override val returnMessage: String?,
			val rankedConquest: RankedItem,
			val rankedDuel: RankedItem,
			val rankedJoust: RankedItem,
			val rankedConquestController: RankedItem,
			val rankedDuelController: RankedItem,
			val rankedJoustController: RankedItem
) : Player {
	val tierConquest = rankedConquest.tier
	val tierDuel = rankedDuel.tier
	val tierJoust = rankedJoust.tier
}

data class GodRank(
			val godId: Long,
			val losses: Int,
			val playerId: Long,
			val playerName: String,
			val playerRanking: Float,
			val rank: Int,
			val wins: Int
)

data class Achievements(
			override val returnMessage: String?,
			@SerializedName("AssistedKills")
			val assistedKills: Int,
			@SerializedName("CampsCleared")
			val campsCleared: Int,
			@SerializedName("Deaths")
			val deaths: Int,
			@SerializedName("DivineSpree")
			val divineSpree: Int,
			@SerializedName("DoubleKills")
			val doubleKills: Int,
			@SerializedName("FireGiantKills")
			val fireGiantKills: Int,
			@SerializedName("FirstBloods")
			val firstBloods: Int,
			@SerializedName("GodLikeSpree")
			val godLikeSpree: Int,
			@SerializedName("GoldFuryKills")
			val goldFuryKills: Int,
			@SerializedName("Id")
			override val id: Long,
			@SerializedName("ImmortalSpree")
			val immortalSpree: Int,
			@SerializedName("KillingSpree")
			val killingSpree: Int,
			@SerializedName("MinionKills")
			val minionKills: Int,
			@SerializedName("Name")
			val name: String,
			@SerializedName("PentaKills")
			val pentaKills: Int,
			@SerializedName("PhoenixKills")
			val phoenixKills: Int,
			@SerializedName("PlayerKills")
			val playerKills: Int,
			@SerializedName("QuadraKills")
			val quadraKills: Int,
			@SerializedName("RampageSpree")
			val rampageSpree: Int,
			@SerializedName("ShutdownSpree")
			val shutdownSpree: Int,
			@SerializedName("SiegeJuggernautKills")
			val siegeJuggernautKills: Int,
			@SerializedName("TowerKills")
			val towerKills: Int,
			@SerializedName("TripleKills")
			val tripleKills: Int,
			@SerializedName("UnstoppableSpree")
			val unstoppableSpree: Int,
			@SerializedName("WildJuggernautKills")
			val wildJuggernautKills: Int
) : IdObject<Long>, ReturnMessage