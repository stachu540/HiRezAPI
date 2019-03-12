package hirez.json

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
interface IdObject<T> {
	val id: T
}

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
interface Creation {
	val createdAt: Date
}

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
interface Timestamp {
	val timestamp: Date
}

interface ReturnMessage {
	@get:SerializedName("ret_msg")
	val returnMessage: String?
}

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
interface PlayDetailsData {
	val leaves: Long
	val losses: Long
	val name: String
	val wins: Long
}

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
interface Player : PlayDetailsData, Creation, IdObject<Long>, ReturnMessage {
	val activePlayerId: Long
	val mergedPlayers: List<MergedPlayer>?
	@get:SerializedName("Avatar_URL")
	val avatar: String
	val hoursPlayed: Int
	@get:SerializedName("Created_Datetime")
	override val createdAt: Date
	@get:SerializedName("Last_Login_Datetime")
	val lastLogin: Date
	val level: Int
	val masteryLevel: Int
	@get:SerializedName("Personal_Status_Message")
	val statusMessage: String?
	val region: String
	val teamId: Int
	val teamName: String
	val totalAchievements: Int
	val totalWorshippers: Int
	@get:SerializedName("hz_gamer_tag")
	val hiRezGamerTag: String?
	@get:SerializedName("hz_player_name")
	val hiRezPlayerName: String
}

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
interface HeroData<ABILITY : HeroAbility<*>> : IdObject<Long>, ReturnMessage {
	val ability1: ABILITY
	val ability2: ABILITY
	val ability3: ABILITY
	val ability4: ABILITY
	val ability5: ABILITY
	
	val pros: String
	val cons: String
	
	val health: Float
	val lore: String
	val name: String
	val freeRotation: Boolean
	val roles: String
	val speed: Float
	val title: String
}

interface HeroAbility<DESCRIPTION : Serializable> : IdObject<Long> {
	val description: DESCRIPTION
	val name: String
	@get:SerializedName("URL")
	val abilityImage: String
}