package hirez.smite.json

import com.google.gson.annotations.SerializedName


/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class TeamItemSearch(
			@SerializedName("Founder")
			val founder: String,
			@SerializedName("Name")
			val name: String,
			@SerializedName("Players")
			val players: Int,
			@SerializedName("Tag")
			val tag: String,
			@SerializedName("TeamId")
			val teamId: Long
)