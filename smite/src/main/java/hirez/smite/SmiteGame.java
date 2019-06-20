package hirez.smite;

import hirez.api.*;
import hirez.api.object.*;
import hirez.api.object.interfaces.Queue;
import hirez.api.object.interfaces.ReturnedMessage;
import hirez.smite.object.*;
import io.reactivex.Flowable;
import io.reactivex.Single;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

public class SmiteGame {

    private static RestClient REST;

    static boolean isRanked(int id) {
        return Arrays.asList(440, 450, 451, 502, 503, 504).contains(id);
    }

    public static void initConfig(ConfigurationBuilder configuration) {
        if (configuration.getBaseEndpoint() == null) {
            configuration.setBaseEndpoint(SmitePlatform.PC);
        }
        REST = new RestClient(configuration.build());
    }

    private static <T> Single<T> call(Class<T> type, String method, String... args) {
        return REST.get(type, REST.getConfiguration().createUrl(method, args));
    }

    private static <T> Single<T> testAndCall(Class<T> type, String method, String... args) {
        return testSession().flatMap(t -> {
            if (!t.isSuccessful()) {
                return createSession().flatMap(c -> testAndCall(type, method, args));
            } else {
                return call(type, method, args);
            }
        }).flatMap(r -> Single.create(sink -> {
            if (type.isAssignableFrom(ReturnedMessage.class)) {
                ReturnedMessage rm = (type.isArray()) ? ((ReturnedMessage[]) r)[0] : (ReturnedMessage) r;
                if (rm.getReturnedMessage() != null) {
                    sink.onError(new HiRezException(rm));
                } else {
                    sink.onSuccess(r);
                }
            } else {
                sink.onSuccess(r);
            }
        }));
    }

    public static Single<CreateSession> createSession() {
        return call(CreateSession.class, "createsession").doOnSuccess(r -> {
            if (r.getReturnedMessage().equalsIgnoreCase("Approved")) {
                REST.getConfiguration().getSessionStorage().set(r);
            }
        });
    }

    public static Single<TestSession> testSession() {
        return call(String.class, "testsession")
                .map(TestSession::new);
    }

    public static Single<Ping> ping() {
        return call(String.class, "ping")
                .map(Ping::new);
    }

    public static Single<DataUsage> getDataUsed() {
        return testAndCall(DataUsage[].class, "getdataused")
                .map(d -> d[0]);
    }

    public static Flowable<HiRezServer> getHiRezServerStatus() {
        return testAndCall(HiRezServer[].class, "gethirezserverstatus")
                .flattenAsFlowable(Arrays::asList);
    }

    public static Single<PatchInfo> getPatchInfo() {
        return testAndCall(PatchInfo.class, "getpatchinfo");
    }

    public static Flowable<GodLeaderboard> getGodLeaderboard(long godId, Queue queue) {
        return testAndCall(GodLeaderboard[].class, "getgodleaderboard", Long.toString(godId), queue.getId().toString())
                .flattenAsFlowable(Arrays::asList);
    }

    public static Flowable<ItemRecommendation> getGodRecommendedItems(long godId, Language language) {
        return testAndCall(ItemRecommendation[].class, "getgodrecommendeditems", Long.toString(godId), language.getId().toString())
                .flattenAsFlowable(Arrays::asList);
    }

    public static Flowable<God> getGods(Language language) {
        return testAndCall(God[].class, "getgods", language.getId().toString())
                .flattenAsFlowable(Arrays::asList);
    }

    public static Flowable<GodSkin> getGodSkins(long godId, Language language) {
        return testAndCall(GodSkin[].class, "getgodskins", Long.toString(godId), language.getId().toString())
                .flattenAsFlowable(Arrays::asList);
    }

    public static Flowable<Item> getItems(Language language) {
        return testAndCall(Item[].class, "getitems", language.getId().toString())
                .flattenAsFlowable(Arrays::asList);
    }

    public static Single<DemoDetail> getDemoDetails(long matchId) {
        return testAndCall(DemoDetail[].class, "getdemodetails", Long.toString(matchId))
                .map(it -> it[0]);
    }

    public static Flowable<LeagueLeaderboard> getLeagueLeaderboard(Queue queue, Division division, int season) {
        return testAndCall(LeagueLeaderboard[].class, "getleagueleaderboard", queue.getId().toString(), Integer.toString(division.ordinal()), Integer.toString(season))
                .flattenAsFlowable(Arrays::asList);
    }

    public static Single<LeagueSeason[]> getLeagueSeasons(Queue queue) {
        return testAndCall(LeagueSeason[].class, "getleagueseasons", queue.getId().toString());
    }

    public static Flowable<MatchDetail> getMatchDetails(long matchId) {
        return testAndCall(MatchDetail[].class, "getmatchdetails", Long.toString(matchId))
                .flattenAsFlowable(Arrays::asList);
    }

    public static Flowable<MatchDetail> getMatchDetailsBatch(long... matchId) {
        if (matchId.length < 5) {
            return Flowable.error(new HiRezException("To get Batched matches you must defined minimum 5 matchId's"));
        }
        return testAndCall(MatchDetail[].class, "getmatchdetailsbatch", Arrays.stream(matchId).mapToObj(Long::toString).collect(Collectors.joining(",")))
                .flattenAsFlowable(Arrays::asList);
    }

    public static Flowable<MatchHistory> getMatchHistory(long userId) {
        return testAndCall(MatchHistory[].class, "getmatchhistory", Long.toString(userId))
                .flattenAsFlowable(Arrays::asList);
    }

    public static Flowable<MatchId> getMatchIdsByQueue(Queue queue, Date timestamp) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd/HH");
        DateFormat mdf = new SimpleDateFormat("mm");

        int minutes = Integer.parseInt(mdf.format(timestamp));

        minutes = minutes - minutes % 10 + ((minutes % 10) > 4 ? 10 : 0);

        return testAndCall(MatchId[].class, "getmatchidsbyqueue", queue.getId().toString(), df.format(timestamp) + minutes)
                .flattenAsFlowable(Arrays::asList);
    }

    public static Flowable<LiveMatch> getMatchPlayerDetails(long matchId) {
        return testAndCall(LiveMatch[].class, "getmatchplayerdetails", Long.toString(matchId))
                .flattenAsFlowable(Arrays::asList);
    }

    public static Flowable<QueueStat> getQueueStats(long userId, Queue queue) {
        return testAndCall(QueueStat[].class, "getqueuestats", Long.toString(userId), queue.getId().toString())
                .flattenAsFlowable(Arrays::asList);
    }

    public static Flowable<TopMatch> getTopMatches() {
        return testAndCall(TopMatch[].class, "gettopmatches")
                .flattenAsFlowable(Arrays::asList);
    }

    public static Flowable<Friend> getFriends(long userId) {
        return testAndCall(Friend[].class, "getfriends", Long.toString(userId))
                .flattenAsFlowable(Arrays::asList);
    }

    public static Flowable<GodRank> getGodRanks(long userId) {
        return testAndCall(GodRank[].class, "getgodranks", Long.toString(userId))
                .flattenAsFlowable(Arrays::asList);
    }

    public static Single<Player> getPlayer(long userId) {
        return testAndCall(Player[].class, "getplayer", Long.toString(userId))
                .map(it -> it[0]);
    }

    public static Single<Player> getPlayer(long userId, Portal portal) {
        return testAndCall(Player[].class, "getplayer", Long.toString(userId), portal.getId().toString())
                .map(it -> it[0]);
    }

    public static Single<Achievements> getPlayerAchievements(long userId) {
        return testAndCall(Achievements.class, "getplayerachievements", Long.toString(userId));
    }

    public static Flowable<PlayerIdPortal> getPlayerIdByName(String query) {
        return testAndCall(PlayerIdPortal[].class, "getplayeridbyname", query)
                .flattenAsFlowable(Arrays::asList);
    }

    public static Flowable<PlayerIdPortal> getPlayerIdByPortalUserId(Portal portal, long id) {
        return testAndCall(PlayerIdPortal[].class, "getplayeridbyportaluserid", portal.getId().toString(), Long.toString(id))
                .flattenAsFlowable(Arrays::asList);
    }

    public static Flowable<PlayerIdPortal> getPlayerIdsByGamerTag(Portal portal, String query) {
        return testAndCall(PlayerIdPortal[].class, "getplayeridsbygamertag", portal.getId().toString(), query)
                .flattenAsFlowable(Arrays::asList);
    }

    public static Single<PlayerStatus> getPlayerStatus(long userId) {
        return testAndCall(PlayerStatus[].class, "getplayerstatus", Long.toString(userId))
                .map(it -> it[0]);
    }

    public static Single<Team> getTeamDetails(long teamId) {
        return testAndCall(Team[].class, "getteamdetails", Long.toString(teamId))
                .map(it -> it[0]);
    }

    public static Flowable<TeamMember> getTeamPlayers(long teamId) {
        return testAndCall(TeamMember[].class, "getteamplayers", Long.toString(teamId))
                .flattenAsFlowable(Arrays::asList);
    }

    public static Flowable<TeamQuery> searchTeams(String query) {
        return testAndCall(TeamQuery[].class, "searchteams", query)
                .flattenAsFlowable(Arrays::asList);
    }

    public static Flowable<ProLeagueDetail> getEsportsProLeagueDetails() {
        return testAndCall(ProLeagueDetail[].class, "getesportsproleaguedetails")
                .flattenAsFlowable(Arrays::asList);
    }

    public static Flowable<MatchOfTheDay> getMOTD() {
        return testAndCall(MatchOfTheDay[].class, "getmotd")
                .flattenAsFlowable(Arrays::asList);
    }

    public static Flowable<PlayerQuery> searchPlayer(String query) {
        return testAndCall(PlayerQuery[].class, "searchplayers", query)
                .flattenAsFlowable(Arrays::asList);
    }

    public static StatusPage getStatusPage() {
        return new StatusPage(REST);
    }
}
