package hirez.realm.json

import com.google.gson.annotations.SerializedName
import hirez.json.ReturnMessage


/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class Talent(
			val categoryName: String,
			val itemId: Int,
			val lootTableItemId: Int,
			@SerializedName("ret_msg")
			override val returnMessage: String?,
			val talentDescription: String,
			val talentName: String
) : ReturnMessage