package hirez.paladins;

import hirez.api.*;
import hirez.api.object.*;
import hirez.api.object.interfaces.Queue;
import hirez.api.object.interfaces.ReturnedMessage;
import hirez.paladins.object.*;
import io.reactivex.Flowable;
import io.reactivex.Single;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

public class Paladins {

    private static RestClient REST;

    public static void initConfig(ConfigurationBuilder configuration) {
        if (configuration.getBaseEndpoint() == null) {
            configuration.setBaseEndpoint(PaladinsPlatform.PC);
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

    public static Flowable<ChampionCard> getChampionCards(long championId, Language language) {
        return testAndCall(ChampionCard[].class, "getchampioncards", Long.toString(championId), language.getId().toString())
                .flattenAsFlowable(Arrays::asList);
    }

    public static Flowable<ChampionLeaderboard> getChampionLeaderboard(long championId, Queue queue) {
        return testAndCall(ChampionLeaderboard[].class, "getchampionleaderboard", Long.toString(championId), queue.getId().toString())
                .flattenAsFlowable(Arrays::asList);
    }

    public static Flowable<Champion> getChampions(Language language) {
        return testAndCall(Champion[].class, "getchampions", language.getId().toString())
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
        DateFormat df = new SimpleDateFormat("yyyyMMdd/hh");
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

    public static Flowable<ChampionRank> getChampionRanks(long userId) {
        return testAndCall(ChampionRank[].class, "getchampionranks", Long.toString(userId))
                .flattenAsFlowable(Arrays::asList);
    }

    public static Flowable<Friend> getFriends(long userId) {
        return testAndCall(Friend[].class, "getfriends", Long.toString(userId))
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

    public static Single<PlayerIdXboxSwitch[]> getPlayerIdInfoForXboxAndSwitch() {
        return testAndCall(PlayerIdXboxSwitch[].class, "getplayeridinfoforxboxandswitch", "stachu%20official");
    }

    public static Flowable<PlayerLoadout> getPlayerLoadouts(String user, Language language) {
        return testAndCall(PlayerLoadout[].class, "getplayerloadouts", user, language.getId().toString())
                .flattenAsFlowable(Arrays::asList);
    }

    public static Flowable<PlayerLoadout> getPlayerLoadouts(Long id) {
        return getPlayerLoadouts(id, REST.getConfiguration().getLanguage());
    }

    public static Flowable<PlayerLoadout> getPlayerLoadouts(String user) {
        return getPlayerLoadouts(user, REST.getConfiguration().getLanguage());
    }

    public static Flowable<PlayerLoadout> getPlayerLoadouts(Long id, Language language) {
        return getPlayerLoadouts(Long.toString(id), language);
    }

    public static Single<PlayerStatus> getPlayerStatus(String user) {
        return testAndCall(PlayerStatus[].class, "getplayerstatus", user)
                .map(it -> it[0]);
    }

    public static Single<PlayerStatus> getPlayerStatus(long id) {
        return getPlayerStatus(Long.toString(id));
    }

    public static Single<ProLeagueDetail[]> getEsportsProLeagueDetails() {
        return testAndCall(ProLeagueDetail[].class, "getesportsproleaguedetails");
    }

    public static Flowable<PlayerQuery> searchPlayer(String query) {
        return testAndCall(PlayerQuery[].class, "searchplayers", query)
                .flattenAsFlowable(Arrays::asList);
    }

    public static StatusPage getStatusPage() {
        return new StatusPage(REST);
    }
}
