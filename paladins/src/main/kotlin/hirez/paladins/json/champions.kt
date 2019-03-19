package hirez.paladins.json

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import hirez.json.IdObject
import hirez.json.ReturnMessage
import hirez.json.adapters.BooleanTextDeserializer
import java.util.*

data class Champion(
			@SerializedName("Ability_1")
			val ability1: Champion.Ability,
			@SerializedName("Ability_2")
			val ability2: Champion.Ability,
			@SerializedName("Ability_3")
			val ability3: Champion.Ability,
			@SerializedName("Ability_4")
			val ability4: Champion.Ability,
			@SerializedName("Ability_5")
			val ability5: Champion.Ability,
			@SerializedName("ChampionCard_URL")
			val cardUrl: String,
			@SerializedName("ChampionIcon_URL")
			val iconUrl: String,
			@SerializedName("Cons")
			val cons: String,
			@SerializedName("Health")
			val health: Float,
			@SerializedName("Lore")
			val lore: String,
			@SerializedName("Name")
			val name: String,
			@SerializedName("OnFreeRotation")
			@JsonAdapter(BooleanTextDeserializer::class)
			val freeRotation: Boolean,
			@JsonAdapter(BooleanTextDeserializer::class)
			@SerializedName("OnFreeWeeklyRotation")
			val freeWeeklyRotation: Boolean,
			@SerializedName("Pantheon")
			val pantheon: String,
			@SerializedName("Pros")
			val pros: String,
			@SerializedName("Roles")
			val roles: String,
			@SerializedName("Speed")
			val speed: Float,
			@SerializedName("Title")
			val title: String,
			override val id: Long,
			@SerializedName("Type")
			val type: String,
			@JsonAdapter(BooleanTextDeserializer::class)
			val latestChampion: Boolean,
			@SerializedName("ret_msg")
			override val returnMessage: String?
) : ReturnMessage, IdObject<Long> {
	data class Ability(
				@SerializedName("Description")
				val description: String,
				@SerializedName("Id")
				val id: Long,
				@SerializedName("Summary")
				val name: String,
				@SerializedName("URL")
				val abilityImage: String
	)
}

data class ChampionCard(
			@SerializedName("active_flag_activation_schedule")
			@JsonAdapter(BooleanTextDeserializer::class)
			val activeFlagActivationSchedule: Boolean,
			@SerializedName("active_flag_lti")
			@JsonAdapter(BooleanTextDeserializer::class)
			val activeFlagLti: Boolean,
			@SerializedName("card_description")
			val description: String,
			@SerializedName("card_id1")
			val id1: Long,
			@SerializedName("card_id2")
			val id2: Long,
			@SerializedName("card_name")
			val name: String,
			@SerializedName("card_name_english")
			val nameEnglish: String,
			@SerializedName("championCard_URL")
			val championCardURL: String,
			@SerializedName("championIcon_URL")
			val championIconURL: String,
			val championId: Long,
			val championName: String,
			@JsonAdapter(BooleanTextDeserializer::class)
			val exclusive: Boolean,
			val rank: Int,
			val rarity: String,
			val rechargeSeconds: Long,
			@SerializedName("ret_msg")
			override val returnMessage: String?
) : ReturnMessage

data class ChampionSkin(
			val championId: Long,
			val championName: String,
			val rarity: String,
			@SerializedName("ret_msg")
			override val returnMessage: String?,
			@SerializedName("skin_id1")
			val id1: Long,
			@SerializedName("skin_id2")
			val id2: Long,
			@SerializedName("skin_name")
			val name: String,
			@SerializedName("skin_name_english")
			val nameEnglish: String
) : ReturnMessage

data class ChampionRank(
			@SerializedName("Assists")
			val assists: Int,
			@SerializedName("Deaths")
			val deaths: Int,
			@SerializedName("Kills")
			val kills: Int,
			val gold: Int,
			@SerializedName("Losses")
			val losses: Int,
			@SerializedName("MinionKills")
			val minionKills: Int,
			val minutes: Long,
			@SerializedName("Rank")
			val rank: Int,
			@SerializedName("Wins")
			val wins: Int,
			@SerializedName("Worshippers")
			val worshippers: Int,
			val champion: String,
			@SerializedName("champion_id")
			val championId: Long,
			@SerializedName("player_id")
			val playerId: Long,
			@SerializedName("LastPlayed")
			val lastPlayed: Date,
			override val returnMessage: String?
) : ReturnMessage