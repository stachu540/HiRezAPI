package hirez.paladins.json

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
			val rankedController: RankedItem,
			val rankedKBM: RankedItem
) : ReturnMessage, IdObject<Long> {
	val tierConquest = rankedConquest.tier
	val tierRankedController = rankedController.tier
	val tierRankedKBM = rankedKBM.tier
}

data class ChampionLeaderboard(
			@SerializedName("champion_id")
			val championId: Long,
			val losses: Int,
			@SerializedName("player_id")
			val playerId: Long,
			@SerializedName("player_name")
			val playerName: String,
			@SerializedName("player_ranking")
			val playerRanking: Float,
			val rank: Int,
			@SerializedName("ret_msg")
			override val returnMessage: String?,
			val wins: Int
) : ReturnMessage

data class Loadout(
			@SerializedName("ChampionId")
			val championId: Long,
			@SerializedName("ChampionName")
			val championName: String,
			@SerializedName("DeckId")
			val deckId: Long,
			@SerializedName("DeckName")
			val deckName: String,
			@SerializedName("LoadoutItems")
			val items: List<CardItem>,
			val playerId: Long,
			val playerName: String,
			@SerializedName("ret_msg")
			override val returnMessage: String?
) : ReturnMessage {
	data class CardItem(
				@SerializedName("ItemId")
				override val id: Long,
				@SerializedName("ItemName")
				val name: String,
				@SerializedName("Points")
				val points: Int
	) : IdObject<Long>
}

data class PlatformPlayer(
			@SerializedName("Name")
			val name: String,
			@SerializedName("gamer_tag")
			val gamerTag: String,
			val platform: String,
			@SerializedName("player_id")
			val playerId: Long,
			@SerializedName("portal_userid")
			val portalUserid: Long,
			@SerializedName("ret_msg")
			override val returnMessage: String?
) : ReturnMessage