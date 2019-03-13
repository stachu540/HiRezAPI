package hirez.smite.json

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import hirez.json.HeroAbility
import hirez.json.HeroData
import hirez.json.ReturnMessage
import hirez.json.adapters.BooleanTextDeserializer
import java.io.Serializable

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class God(
			@SerializedName("Ability_1")
			override val ability1: GodAbility,
			@SerializedName("Ability_2")
			override val ability2: GodAbility,
			@SerializedName("Ability_3")
			override val ability3: GodAbility,
			@SerializedName("Ability_4")
			override val ability4: GodAbility,
			@SerializedName("Ability_5")
			override val ability5: GodAbility,
			override val returnMessage: String?,
			@SerializedName("AttackSpeed")
			val attackSpeed: Float,
			@SerializedName("AttackSpeedPerLevel")
			val attackSpeedPerLevel: Float,
			@SerializedName("Cons")
			override val cons: String,
			@SerializedName("HP5PerLevel")
			val hP5PerLevel: Float,
			@SerializedName("Health")
			override val health: Float,
			@SerializedName("HealthPerFive")
			val healthPerFive: Float,
			@SerializedName("HealthPerLevel")
			val healthPerLevel: Float,
			@SerializedName("Lore")
			override val lore: String,
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
			override val name: String,
			@SerializedName("OnFreeRotation")
			@JsonAdapter(BooleanTextDeserializer::class)
			override val freeRotation: Boolean,
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
			override val pros: String,
			@SerializedName("Roles")
			override val roles: String,
			@SerializedName("Speed")
			override val speed: Float,
			@SerializedName("Title")
			override val title: String,
			@SerializedName("Type")
			val type: String,
			val basicAttack: BaseDescription,
			@SerializedName("godAbility1_URL")
			val godAbility1URL: String,
			@SerializedName("godAbility2_URL")
			val godAbility2URL: String,
			@SerializedName("godAbility3_URL")
			val godAbility3URL: String,
			@SerializedName("godAbility4_URL")
			val godAbility4URL: String,
			@SerializedName("godAbility5_URL")
			val godAbility5URL: String,
			@SerializedName("godCard_URL")
			val godCardURL: String,
			@SerializedName("godIcon_URL")
			val godIconURL: String,
			override val id: Long,
			@JsonAdapter(BooleanTextDeserializer::class)
			val latestGod: Boolean
) : HeroData<GodAbility> {
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
}

data class GodAbility(
			@SerializedName("Description")
			override val description: BaseDescription,
			@SerializedName("Id")
			override val id: Long,
			@SerializedName("Summary")
			override val name: String,
			@SerializedName("URL")
			override val abilityImage: String
) : HeroAbility<BaseDescription>

data class BaseDescription(
			val itemDescription: ItemedDescription
) : Serializable {
	data class ItemedDescription(
				val cooldown: String,
				val cost: String,
				val description: String,
				val menuitems: List<Item>,
				val rankitems: List<Item>,
				val secondaryDescription: String
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
			val name: String
)

data class UserGodRank(
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
			val godId: String,
			@SerializedName("player_id")
			val playerId: String
)