package hirez.smite.json

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import hirez.Language
import hirez.api.Queue
import hirez.json.ReturnMessage
import hirez.json.adapters.BooleanTextDeserializer
import hirez.smite.SmiteGame

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class God(
			@SerializedName("Ability_1")
			val ability1: God.Ability,
			@SerializedName("Ability_2")
			val ability2: God.Ability,
			@SerializedName("Ability_3")
			val ability3: God.Ability,
			@SerializedName("Ability_4")
			val ability4: God.Ability,
			@SerializedName("Ability_5")
			val ability5: God.Ability,
			@SerializedName("ret_msg")
			override val returnMessage: String?,
			@SerializedName("AttackSpeed")
			val attackSpeed: Float,
			@SerializedName("AttackSpeedPerLevel")
			val attackSpeedPerLevel: Float,
			@SerializedName("Cons")
			val cons: String,
			@SerializedName("HP5PerLevel")
			val hP5PerLevel: Float,
			@SerializedName("Health")
			val health: Float,
			@SerializedName("HealthPerFive")
			val healthPerFive: Float,
			@SerializedName("HealthPerLevel")
			val healthPerLevel: Float,
			@SerializedName("Lore")
			val lore: String,
			@SerializedName("MP5PerLevel")
			val mP5PerLevel: Float,
			@SerializedName("MagicProtection")
			val magicProtection: Float,
			@SerializedName("MagicProtectionPerLevel")
			val magicProtectionPerLevel: Float,
			@SerializedName("MagicalPower")
			val magicalPower: Float,
			@SerializedName("MagicalPowerPerLevel")
			val magicalPowerPerLevel: Float,
			@SerializedName("Mana")
			val mana: Float,
			@SerializedName("ManaPerFive")
			val manaPerFive: Float,
			@SerializedName("ManaPerLevel")
			val manaPerLevel: Int,
			@SerializedName("Name")
			val name: String,
			@SerializedName("OnFreeRotation")
			@JsonAdapter(BooleanTextDeserializer::class)
			val freeRotation: Boolean,
			@SerializedName("Pantheon")
			val pantheon: String,
			@SerializedName("PhysicalPower")
			val physicalPower: Float,
			@SerializedName("PhysicalPowerPerLevel")
			val physicalPowerPerLevel: Float,
			@SerializedName("PhysicalProtection")
			val physicalProtection: Float,
			@SerializedName("PhysicalProtectionPerLevel")
			val physicalProtectionPerLevel: Double,
			@SerializedName("Pros")
			val pros: String,
			@SerializedName("Roles")
			val roles: String,
			@SerializedName("Speed")
			val speed: Float,
			@SerializedName("Title")
			val title: String,
			@SerializedName("Type")
			val type: String,
			val basicAttack: BaseDescription,
			@SerializedName("godCard_URL")
			val cardUrl: String,
			@SerializedName("godIcon_URL")
			val iconUrl: String,
			val id: Long,
			@JsonAdapter(BooleanTextDeserializer::class)
			val latestGod: Boolean
) : ReturnMessage {
	val abilityDescription1
		get() = ability1.description
	val abilityDescription2
		get() = ability2.description
	val abilityDescription3
		get() = ability3.description
	val abilityDescription4
		get() = ability4.description
	val abilityDescription5
		get() = ability5.description
	
	fun getLeaderboard(queue: Queue) =
				SmiteGame.getGodLeaderboard(id, queue)
	
	fun getSkins(language: Language) =
				SmiteGame.getGodSkins(id, language)
	
	val skins
		get() = SmiteGame.getGodSkins(id)
	
	fun getRecommendedItems(language: Language) =
				SmiteGame.getGodRecommendedItems(id, language)
	
	val recommendedItems
		get() = SmiteGame.getGodRecommendedItems(id)
	
	data class Ability(
				@SerializedName("Description")
				val description: BaseDescription,
				@SerializedName("Id")
				val id: Long,
				@SerializedName("Summary")
				val name: String,
				@SerializedName("URL")
				val abilityImage: String
	)
}

data class BaseDescription(
			val itemDescription: ItemedDescription
) {
	data class ItemedDescription(
				val cooldown: String,
				val cost: String,
				val description: String,
				val menuitems: List<Item>,
				val rankitems: List<Item>,
				val secondaryDescription: String?
	)
	
	data class Item(
				val description: String,
				val value: String
	)
}

data class GodSkin(
			@SerializedName("godIcon_URL")
			val iconUrl: String,
			@SerializedName("godSkin_URL")
			val skinUrl: String,
			@SerializedName("god_id")
			val godId: Long,
			@SerializedName("god_name")
			val godName: String,
			val obtainability: String,
			@SerializedName("price_favor")
			val priceFavor: Int,
			@SerializedName("price_gems")
			val priceGems: Int,
			@SerializedName("skin_id1")
			val Id1: Long,
			@SerializedName("skin_id2")
			val Id2: Long,
			@SerializedName("skin_name")
			val name: String,
			override val returnMessage: String?
) : ReturnMessage

data class GodLeaderboard(
			@SerializedName("ret_msg")
			override val returnMessage: String?,
			val godId: Long,
			val losses: Int,
			val playerId: Long,
			val playerName: String,
			val playerRanking: Float,
			val rank: Int,
			val wins: Int
) : ReturnMessage