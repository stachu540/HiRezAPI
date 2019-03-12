package hirez.paladins.json

import hirez.json.MergedPlayer
import hirez.json.Player
import hirez.json.RankedItem
import java.time.Instant
import java.util.*

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class PaladinsPlayer(
    override val id: Long,
    override val leaves: Long,
    override val losses: Long,
    override val name: String,
    override val wins: Long,
    override val activePlayerId: Long,
    override val mergedPlayers: Array<MergedPlayer>?,
    override val avatar: String,
    override val hoursPlayed: Int,
    override val createdAt: Date,
    override val lastLogin: Date,
    override val level: Int,
    override val masteryLevel: Int,
    override val statusMessage: String?,
    override val region: String,
    override val teamId: Int,
    override val teamName: String,
    override val totalAchievements: Int,
    override val totalWorshippers: Int,
    val rankedConquest: RankedItem,
    val rankedController: RankedItem,
    val rankedKBM: RankedItem,
    val tierConquest: RankedItem.Tier,
    val tierRankedController: RankedItem.Tier,
    val tierRankedKBM: RankedItem.Tier
) : Player
