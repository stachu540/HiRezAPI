package hirez.smite

import com.google.gson.Gson
import hirez.Config
import hirez.ConfigBuilder
import hirez.Language
import hirez.api.AbstractAPI
import hirez.api.Http
import hirez.api.Queue
import hirez.api.exceptions.PlayerNotFoundException
import hirez.enums.Portal
import hirez.flattenArray
import hirez.json.CreateSession
import hirez.json.DataUsage
import hirez.json.Item
import hirez.json.Patch
import hirez.json.Ping
import hirez.json.PlayerData
import hirez.json.Server
import hirez.json.TestSession
import hirez.smite.json.God
import hirez.smite.json.GodLeaderboard
import hirez.smite.json.GodSkin
import hirez.smite.json.Player
import hirez.statuspage.StatusPage
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
object SmiteGame : AbstractAPI() {
	@JvmStatic
	fun initConfig(config: Config) {
		this.config = config
		this.http = Http(Gson(), config.userAgent)
	}
	
	@JvmStatic
	fun initConfig(configBuilder: ConfigBuilder.() -> Unit) =
				initConfig(ConfigBuilder().apply(configBuilder).build())
	
	
	@JvmStatic
	fun createsession() = get<CreateSession>("createsession").doOnSuccess(config.sessionStorage::set)
	val dataused
		@JvmStatic get() = get<DataUsage>("getdataused")
	val hirezserverstatus
		@JvmStatic get() = get<Array<Server>>("gethirezserverstatus")
	val patchinfo
		@JvmStatic get() = get<Patch>("getpatchinfo")
	
	@JvmStatic
	fun ping() = get<String>("ping").map(::Ping)
	
	@JvmStatic
	fun testsession() = get<String>("testsession")
				.map(::TestSession)
				.doOnSuccess { if (!it.isSuccessful) config.sessionStorage.remove() }
	
	fun getGodLeaderboard(godId: Long, queue: Queue) =
				Single.just(queue).flatMap { if (queue.isRanked) get<Array<GodLeaderboard>>("getgodleaderboard") else Single.error(IllegalArgumentException("Only ranked games are be allowed")) }
							.flattenArray()
	fun getGodRecommendedItems() =
				get<Array<Item>>("getgodrecommendeditems")
							.flattenArray()
	fun getGods() =
				get<Array<God>>("getgods")
							.flattenArray()
	fun getGodSkins() =
				get<Array<GodSkin>>("getgodskins")
							.flattenArray()
	fun getItems() =
				get<Array<Item>>("getitems")
							.flattenArray()
	fun getDemoDetails() =
				get<>("getdemodetails")
	fun getLeagueLeaderboard() =
				get<>("getleagueleaderboard")
	fun getLeagueSeasons() =
				get<>("getleagueseasons")
	fun getMatchDetails() =
				get<>("getmatchdetails")
	fun getMatchDetailsBatch() =
				get<>("getmatchdetailsbatch")
	fun getMatchHistory() =
				get<>("getmatchhistory")
	fun getMatchIdsByQueue() =
				get<>("getmatchidsbyqueue")
	fun getMatchPlayerDetails() =
				get<>("getmatchplayerdetails")
	fun getQueueStats() =
				get<>("getqueuestats")
	fun getTopMatches() =
				get<>("gettopmatches")
	fun getFriends() =
				get<>("getfriends")
	fun getGodRanks() =
				get<>("getgodranks")
	fun getPlayer() =
				get<>("getplayer")
	fun getPlayerAchievements() =
				get<>("getplayerachievements")
	fun getPlayerIdByName() =
				get<>("getplayeridbyname")
	fun getPlayerIdByPortalUserId() =
				get<>("getplayeridbyportaluserid")
	fun getPlayerIdsByGamertag() =
				get<>("getplayeridsbygamertag")
	fun getPlayerStatus() =
				get<>("getplayerstatus")
	fun getTeamDetails() =
				get<>("getteamdetails")
	fun getTeamPlayers() =
				get<>("getteamplayers")
	fun searchTeams() =
				get<>("searchteams")
	fun getEsportsProLeagueDetails() =
				get<>("getesportsproleaguedetails")
	fun getMOTD() =
				get<>("getmotd")

//	@JvmStatic
//	fun ping() = get<String>("ping")
//				.map(::Ping)
//
//	@JvmStatic
//	fun createSession() =
//				get<CreateSession>("createsession")
//							.doOnSuccess(config.sessionStorage::set)
//
//	@JvmStatic
//	fun testSession() = get<String>("testsession")
//				.map(::TestSession)
//				.doOnSuccess { if (!it.isSuccessful) config.sessionStorage.remove() }
//
//	@JvmStatic
//	val dataUsed
//		get() = get<Array<DataUsage>>("getdataused")
//					.map { it[0] }
//
//	@JvmStatic
//	val patchInfo
//		get() = get<Patch>("getpatchinfo")
//
//	@JvmStatic
//	val statusPage
//		get() = StatusPage(http)
//
//	@JvmStatic
//	val hiRezServerStatus
//		get() = get<Array<Server>>("gethirezserverstatus")
//					.flattenAsFlowable { it.asIterable() }
//	@JvmStatic
//	val gods
//		get() = getGods(config.defaultLanguage)
//
//	@JvmStatic
//	fun getGods(language: Language) =
//				get<Array<God>>("getgods", language.id.toString())
//							.flattenAsFlowable { it.asIterable() }
//
//	@JvmStatic
//	fun getGodLeaderboard(godId: Long, queue: Queue) = get<Array<GodLeaderboard>>("getgodleaderboard", godId.toString(), queue.id.toString())
//				.flattenAsFlowable { it.asIterable() }
//
//	@JvmStatic
//	fun getGodSkins(godId: Long, language: Language) =
//				get<Array<GodSkin>>("", godId.toString(), language.id.toString())
//							.flattenAsFlowable { it.asIterable() }
//
//	@JvmStatic
//	fun getGodSkins(godId: Long) = SmiteGame.getGodSkins(godId, config.defaultLanguage)
//
//	@JvmStatic
//	fun getGodRecommendedItems(godId: Long, language: Language) =
//				get<Array<Item>>("getgodrecommendeditems", godId.toString(), language.id.toString())
//							.flattenAsFlowable { it.asIterable() }
//
//	@JvmStatic
//	fun getGodRecommendedItems(godId: Long) = getGodRecommendedItems(godId, config.defaultLanguage)
//
//	@JvmStatic
//	fun getItems(language: Language) =
//				get<Array<Item>>("getitems", language.id.toString())
//							.flattenAsFlowable { it.asIterable() }
//
//	@JvmStatic
//	val items
//		get() = getItems(config.defaultLanguage)
//
//	@JvmStatic
//	fun getPlayer(name: String) =
//				getPlayerIdByName(name).flatMap { get<Array<Player>>("getplayer", it[0].id.toString()) }
//							.map { if (it.isNotEmpty()) it[0] else throw PlayerNotFoundException("This profile is hidden: $name") }
//
//	@JvmStatic
//	fun getPlayer(name: String, portal: Portal) =
//				getPlayerIdByName(name).flatMap {
//					val p = it.firstOrNull { it.portal == portal }
//					if (p != null) get<Array<Player>>("getplayer", p.id.toString(), p.portal.id.toString())
//					else Single.error(PlayerNotFoundException("Player: $name is not assignable to this portal: ${portal.name}"))
//				}.map { if (it.isNotEmpty()) it[0] else throw PlayerNotFoundException("This profile is hidden: $name") }
//
//	@JvmStatic
//	fun getPlayerIdByName(name: String) =
//				get<Array<PlayerData>>("getplayeridbyname", name)
//							.map { if (it.isNotEmpty()) it else throw PlayerNotFoundException("Player does not exist: $name") }
//
//	@JvmStatic
//	fun getPlayerIdByPortalUserId(userId: Long, portal: Portal) =
//				get<Array<PlayerData>>("getplayeridbyportaluserid", portal.id.toString(), userId.toString())
//							.map { if (it.isNotEmpty()) it else throw PlayerNotFoundException("Player does not exist: ID[$userId]") }
//
//	@JvmStatic
//	fun getPlayerIdByGamertag(gamertag: String, portal: Portal) =
//				get<Array<PlayerData>>("getplayeridbyportaluserid", portal.id.toString(), gamertag)
//							.map { if (it.isNotEmpty()) it else throw PlayerNotFoundException("Player does not exist: $gamertag") }
	
}