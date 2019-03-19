package hirez.paladins

import com.google.gson.Gson
import hirez.Config
import hirez.ConfigBuilder
import hirez.Language
import hirez.api.AbstractAPI
import hirez.api.Http
import hirez.api.Queue
import hirez.enums.Portal
import hirez.format
import hirez.json.CreateSession
import hirez.json.DataUsage
import hirez.json.Item
import hirez.json.MOTD
import hirez.json.Patch
import hirez.json.Ping
import hirez.json.PlayerData
import hirez.json.PlayerState
import hirez.json.ProLeagueDetail
import hirez.json.QueueMatch
import hirez.json.RankedItem
import hirez.json.Server
import hirez.json.Team
import hirez.json.TeamItem
import hirez.json.TeamMember
import hirez.json.TestSession
import hirez.paladins.json.Champion
import hirez.paladins.json.ChampionCard
import hirez.paladins.json.ChampionLeaderboard
import hirez.paladins.json.ChampionRank
import hirez.paladins.json.ChampionSkin
import hirez.paladins.json.Loadout
import hirez.paladins.json.PlatformPlayer
import hirez.paladins.json.Player
import hirez.statuspage.StatusPage
import io.reactivex.Single
import java.util.*

/**
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
object Paladins : AbstractAPI() {
	
	@JvmStatic
	fun initConfig(config: Config) {
		this.config = config
		this.http = Http(Gson(), config.userAgent)
	}
	
	@JvmStatic
	fun initConfig(configBuilder: ConfigBuilder.() -> Unit) =
				initConfig(ConfigBuilder().apply(configBuilder).build())
	
	@JvmStatic
	fun createSession() = get<CreateSession>("createsession").doOnSuccess(config.sessionStorage::set)
	
	val dataUsed
		@JvmStatic get() = get<DataUsage>("getdataused")
	val hiRezServerStatus
		@JvmStatic get() = getCollection<Server>("gethirezserverstatus")
	val patchInfo
		@JvmStatic get() = get<Patch>("getpatchinfo")
	
	@JvmStatic
	fun ping() = getAndMap("ping", mapper = ::Ping)
	
	@JvmStatic
	fun testSession() = getAndMap("testsession", mapper = ::TestSession)
				.doOnSuccess { if (!it.isSuccessful) config.sessionStorage.remove() }
	
	fun getChampionCards(championId: Long, language: Language) =
				getFlattenIterable<ChampionCard>("getchampioncards", championId.toString(), language.id.toString())
	
	fun getChampionLeaderboard(championId: Long, queue: Queue) =
				getFlattenIterable<ChampionLeaderboard>("getchampionleaderboard", championId.toString(), queue.id.toString())
	
	fun getChampions(language: Language) =
				getFlattenIterable<Champion>("getchampions", language.id.toString())
	
	fun getChampionSkins(championId: Long, language: Language) =
				getFlattenIterable<ChampionSkin>("getchampionskins", championId.toString(), language.id.toString())
	
	fun getItems(language: Language) =
				getFlattenIterable<Item>("getitems", language.id.toString())
	
	fun getDemoDetails(matchId: Long) =
				get<QueueMatch>("getdemodetails", matchId.toString())
	
	fun getLeagueLeaderboard(queue: Queue, tier: RankedItem.Tier, split: Int) =
				get<>("getleagueleaderboard", queue.id.toString(), tier.ordinal.toString(), split.toString())
	
	fun getLeagueSeasons(queue: Queue) =
				get<>("getleagueseasons", queue.id.toString())
	
	fun getMatchDetails(matchId: Long) =
				getCollection<MatchStat>("getmatchdetails", matchId.toString())
	
	fun getMatchDetailsBatch(vararg matchIds: Long) {
		val matches = matchIds.copyOf(10)
		return if (matches.size > 5) Single.error(IllegalStateException("Must initialize minimum 5 match ID's")) else getCollection<MatchStat>("getmatchdetailsbatch", matchIds.joinToString(",") { it.toString() }))
	}
	
	fun getMatchHistory(player: String) =
				get<>("getmatchhistory", player)
	
	fun getMatchIdsByQueue(queue: Queue, timestamp: Calendar) =
				getFlattenIterable<QueueMatch>("getmatchidsbyqueue", timestamp.apply { set(Calendar.MINUTE, get(Calendar.MINUTE).let { it - (it % 10) }) }.format("yyyyMMdd/HHmm"))
	
	fun getMatchPlayerDetails(matchId: Long) =
				get<>("getmatchplayerdetails")
	
	fun getQueueStats(player: String, queue: Queue) =
				get<>("getqueuestats")
	
	val topMatches
		get() = get<>("gettopmatches")
	
	fun getChampionRanks(player: String) =
				getFlattenIterable<ChampionRank>("getchampionranks", player)
	
	fun getFriends(player: String) =
				get<>("getfriends")
	
	fun getPlayer(player: String) =
				getCollectionFirst<Player>("getplayer")
	
	fun getPlayer(player: String, portal: Portal) =
				getCollectionFirst<Player>("getplayer")
	
	fun getPlayerIdByName(player: String) =
				getFlattenIterable<PlayerData>("getplayeridbyname")
	
	fun getPlayerIdByPortalUserId(portal: Portal, playerId: Long) =
				getFlattenIterable<PlayerData>("getplayeridbyportaluserid", portal.id.toString(), playerId.toString())
	
	fun getPlayerIdInfoForXboxAndSwitch(player: String) =
				getFlattenIterable<PlatformPlayer>("getplayeridinfoforxboxandswitch", player)
	
	fun getPlayerIdsByGamertag(portal: Portal, player: String) =
				get<>("getplayeridsbygamertag")
	
	fun getPlayerLoadouts(player: String, language: Language) =
				getFlattenIterable<Loadout>("getplayerloadouts", player, language.id.toString())
	
	fun getPlayerLoadouts(player: String) = getPlayerLoadouts(player, config.defaultLanguage)
	
	fun getPlayerStatus(player: String) =
				get<PlayerState>("getplayerstatus", player)
	
	fun getTeamDetails(teamId: Long) =
				getCollectionFirst<Team>("getteamdetails", teamId.toString())
	
	fun getTeamPlayers(teamId: Long) =
				getFlattenIterable<TeamMember>("getteamplayers", teamId.toString())
	
	val esportsProLeagueDetails
		get() = getFlattenIterable<ProLeagueDetail>("getesportsproleaguedetails")
	
	val MOTD
		get() = getFlattenIterable<MOTD>("getmotd")
	
	@JvmStatic
	val statusPage
		get() = StatusPage(http)
	
	@JvmStatic
	val items
		get() = getItems(config.defaultLanguage)
	
	val champions
		get() = getChampions(config.defaultLanguage)
	
	fun getChampionCards(championId: Long) = getChampionCards(championId, config.defaultLanguage)
	
	fun getChampionSkins(championId: Long) = getChampionSkins(championId, config.defaultLanguage)
	
}
