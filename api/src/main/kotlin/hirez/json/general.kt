package hirez.json

import com.google.gson.annotations.SerializedName
import hirez.enums.Portal
import hirez.toRomanNumber
import java.util.*

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class Friend(
			val accountId: Long,
			val avatarUrl: String,
			val name: String,
			val playerId: Long
)

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class Item(
			val childItemId: Int,
			val deviceName: String,
			val iconId: Int,
			val itemDescription: ItemDescription,
			val itemId: Int,
			val itemTier: Int,
			val price: Int,
			val restrictedRoles: String,
			val rootItemId: Int,
			val shortDesc: String,
			val startingItem: Boolean,
			val type: String,
			val itemIconURL: String
)

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class PlayerData(
			@SerializedName("player_id")
			override val id: Long,
			@SerializedName("portal")
			val portalName: String,
			@SerializedName("portal_id")
			val portalId: Int
) : IdObject<Long> {
	val portal: Portal = Portal.values().firstOrNull { it.id == portalId } ?: Portal.UNKNOWN
}

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class PlayerState(
			val match: Int,
			val personalStatusMessage: String,
			val status: Status,
			val statusString: String
) {
	enum class Status { OFFLINE, IN_LOBBY, GOD_SELECTION, IN_GAME, ONLINE, UNKNOWN }
}

data class TeamItem(
			val founder: String,
			val founderId: Long,
			val losses: Int,
			val name: String,
			val players: Int,
			val rating: Int,
			val tag: String,
			val teamId: Long,
			val wins: Int
)

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class MergedPlayer(
			val mergeDatetime: Date,
			val playerId: String,
			val portalId: String
)

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class ItemDescription(
			val description: String,
			val menuitems: Array<Menuitem>,
			val secondaryDescription: String?
)

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class Menuitem(
			val description: String,
			val value: String
)

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class RankedItem(
			val leaves: Int,
			val losses: Int,
			val name: String,
			val points: Int,
			val prevRank: Int,
			val rank: Int,
			val season: Int,
			val tier: Tier,
			val trend: Int,
			val wins: Int,
			val playerId: Long
) {
	
	/**
	 * Ranked Tier League.
	 * @author [Damian Staszewski](damian@stachuofficial.tv)
	 * @since 1.0
	 */
	enum class Tier {
		Qualifying,
		Bronze_5,
		Bronze_4,
		Bronze_3,
		Bronze_2,
		Bronze_1,
		Silver_5,
		Silver_4,
		Silver_3,
		Silver_2,
		Silver_1,
		Gold_5,
		Gold_4,
		Gold_3,
		Gold_2,
		Gold_1,
		Platinum_5,
		Platinum_4,
		Platinum_3,
		Platinum_2,
		Platinum_1,
		Diamond_5,
		Diamond_4,
		Diamond_3,
		Diamond_2,
		Diamond_1,
		Master,
		Grandmaster;
		
		val value =
					name.replace(Regex("(.+)_([0-9])")) { "${it.groupValues[1]} ${it.groupValues[2].toInt().toRomanNumber()}" }
		
		override fun toString() = value
	}
}

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
