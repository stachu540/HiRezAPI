package hirez.realm

import com.google.gson.Gson
import hirez.Config
import hirez.ConfigBuilder
import hirez.Language
import hirez.api.AbstractAPI
import hirez.api.BaseEndpoint
import hirez.api.Game
import hirez.api.Http
import hirez.api.Platform
import hirez.api.Queue
import hirez.enums.Portal
import hirez.format
import hirez.json.CreateSession
import hirez.json.DataUsage
import hirez.json.Patch
import hirez.json.Ping
import hirez.json.PlayerData
import hirez.json.PlayerState
import hirez.json.QueueMatch
import hirez.json.Server
import hirez.json.TestSession
import hirez.realm.json.Match
import hirez.realm.json.MatchHistory
import hirez.realm.json.Player
import hirez.realm.json.PlayerItem
import hirez.realm.json.PlayerStat
import hirez.realm.json.QueueLeaderboard
import hirez.realm.json.Talent
import hirez.statuspage.StatusPage
import java.util.*

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
object RealmRoyale : AbstractAPI() {
	@JvmStatic
	fun initConfig(config: Config) {
		this.config = reconfigure(config)
		this.http = Http(Gson(), config.userAgent)
	}
	
	private fun reconfigure(config: Config): Config =
				ConfigBuilder().apply {
					setDevId(config.devId)
					setAuthKey(config.authKey)
					setDefaultLanguage(config.defaultLanguage)
					setSessionStorage(config.sessionStorage)
					setUserAgent(config.userAgent)
					setEndpoint(object : BaseEndpoint {
						override val game: Game = Game("35lh77mxwjy9", "Realm Royale")
						override val platform: Platform = Platform("4bsbxhr3mrlz", "PC")
						override val baseUrl: String = "https://api.realmroyale.com/realmapi.svc"
					})
				}.build()
	
	@JvmStatic
	fun initConfig(configBuilder: ConfigBuilder.() -> Unit) =
				initConfig(ConfigBuilder().apply(configBuilder).build())
	
	@JvmStatic
	fun createsession() = get<CreateSession>("createsession").doOnSuccess(config.sessionStorage::set)
	val dataused
		@JvmStatic get() = get<DataUsage>("getdataused")
	val hirezserverstatus
		@JvmStatic get() = get<Server>("gethirezserverstatus")
	val patchinfo
		@JvmStatic get() = get<Patch>("getpatchinfo")
	
	@JvmStatic
	fun ping() = get<String>("ping").map(::Ping)
	
	@JvmStatic
	fun testsession() = get<String>("testsession")
				.map(::TestSession)
				.doOnSuccess { if (!it.isSuccessful) config.sessionStorage.remove() }
	
	@JvmStatic
	fun getTalents(language: Language) = get<Array<Talent>>("gettalents", language.id.toString())
	val talents
	
	@JvmStatic get() = getTalents(config.defaultLanguage)
	
	@JvmStatic
	fun getLeaderboard(queue: Queue, rankCriteria: RankCriteria) = get<QueueLeaderboard>("getleaderboard", queue.id.toString(), rankCriteria.id.toString())
	@JvmStatic
	fun getMatchDetails(id: Long) = get<Match>("getmatchdetails", id.toString())
	@JvmStatic
	fun getMatchIdsByQueue(queue: Queue, timestamp: Calendar) =
				get<Array<QueueMatch>>("getmatchidsbyqueue", timestamp.apply { set(Calendar.MINUTE, get(Calendar.MINUTE).let { it - (it % 10) }) }.format("yyyyMMdd/HHmm"))
	
	@JvmStatic
	fun getPlayerMatchHistory(playerId: Long) = get<MatchHistory>("getplayermatchhistory", playerId.toString())
	@JvmStatic
	fun getPlayerMatchHistoryAfterDateTime(playerId: Long, timestamp: Calendar) =
				get<MatchHistory>("getplayermatchhistoryafterdatetime", timestamp.format("yyyyMMddHHmmss"))
	
	@JvmStatic
	fun getPlayerSteam(steamId: Long) = get<Player>("getplayer", steamId.toString(), "steam")
	@JvmStatic
	fun getPlayerHirez(id: Long) = get<Player>("getplayer", id.toString(), "hirez")
	@JvmStatic
	fun getPlayerIdByName(name: String) = get<PlayerData>("getplayeridbyname", name)
	@JvmStatic
	fun getPlayerIdByPortalUserId(portal: Portal, id: Long) = get<PlayerData>("getplayeridbyportaluserid", portal.id.toString(), id.toString())
	@JvmStatic
	fun getPlayerIdsByGamertag(portal: Portal, gamertag: String) = get<Array<PlayerData>>("getplayeridsbygamertag", portal.id.toString(), gamertag)
	@JvmStatic
	fun getPlayerStats(id: Long) = get<PlayerStat>("getplayerstats", id.toString())
	@JvmStatic
	fun getPlayerStatus(id: Long) = get<PlayerState>("getplayerstatus", id.toString())
	@JvmStatic
	fun searchPlayers(query: String) = get<Array<PlayerItem>>("searchplayers", query)
	
	@JvmStatic
	val statusPage
		@JvmStatic
		get() = StatusPage(http)
}