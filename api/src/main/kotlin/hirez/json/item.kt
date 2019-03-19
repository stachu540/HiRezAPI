package hirez.json

import com.google.gson.annotations.SerializedName

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class Item(
			@SerializedName("ret_msg")
			override val returnMessage: String,
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
) : ReturnMessage

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class ItemDescription(
			val description: String,
			val menuitems: List<Menuitem>,
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