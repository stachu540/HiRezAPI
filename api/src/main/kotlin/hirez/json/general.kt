package hirez.json

import com.google.gson.annotations.JsonAdapter
import hirez.api.toRomanNumber
import com.google.gson.annotations.SerializedName
import hirez.json.adapters.CsvLongConverter
import java.util.*


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

data class MOTD(
    val description: String,
    val gameMode: String,
    val maxPlayers: String,
    val name: String,
    @SerializedName("ret_msg")
    override val returnMessage: String?,
    val startDateTime: Date,
		@SerializedName("team1GodsCSV")
		@JsonAdapter(CsvLongConverter::class)
    val team1Picks: List<Long>,
		@SerializedName("team2GodsCSV")
		@JsonAdapter(CsvLongConverter::class)
    val team2Picks: List<Long>,
    val title: String
): ReturnMessage

data class ProLeagueDetail(
    val awayTeamClanId: Int,
    val awayTeamName: String,
    val awayTeamTagname: String,
    val homeTeamClanId: Int,
    val homeTeamName: String,
    val homeTeamTagname: String,
    val mapInstanceId: String,
    @SerializedName("match_date")
    val matchDate: Date,
    val matchNumber: String,
    val matchStatus: String,
    val matchupId: String,
    val region: String,
    @SerializedName("ret_msg")
    override val returnMessage: String?,
    val tournamentName: String,
    val winningTeamClanId: Int
) : ReturnMessage