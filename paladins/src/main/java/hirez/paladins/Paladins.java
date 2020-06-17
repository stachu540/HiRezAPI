package hirez.paladins;

import hirez.api.*;
import hirez.api.object.*;
import hirez.api.object.interfaces.Queue;
import hirez.api.object.interfaces.ReturnedMessage;
import hirez.paladins.object.*;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Paladins extends Endpoint {

    private Paladins(Configuration configuration) {
        super(configuration);
    }

    public static Paladins create(Consumer<ConfigurationBuilder> configuration) {
        return new Paladins(new ConfigurationBuilder().applyFrom((cfg) -> {
            configuration.accept(cfg);
            if (cfg.getBaseEndpoint() == null) {
                cfg.setBaseEndpoint(PaladinsPlatform.PC);
            }
        }).build());
    }

    public Flowable<ChampionCard> getChampionCards(long championId, Language language) {
        return testAndCall(ChampionCard[].class, "getchampioncards", Long.toString(championId), language.getId().toString())
                .flattenAsFlowable(Arrays::asList);
    }

    public Flowable<ChampionCard> getChampionCards(long championId) {
        return getChampionCards(championId, getConfiguration().getLanguage());
    }

    public Flowable<ChampionLeaderboard> getChampionLeaderboard(long championId, Queue queue) {
        return testAndCall(ChampionLeaderboard[].class, "getchampionleaderboard", Long.toString(championId), queue.getId().toString())
                .flattenAsFlowable(Arrays::asList);
    }

    public Flowable<Champion> getChampions(Language language) {
        return testAndCall(Champion[].class, "getchampions", language.getId().toString())
                .flattenAsFlowable(Arrays::asList);
    }

    public Flowable<Champion> getChampions() {
        return getChampions(getConfiguration().getLanguage());
    }

    public Flowable<Item> getItems(Language language) {
        return testAndCall(Item[].class, "getitems", language.getId().toString())
                .flattenAsFlowable(Arrays::asList);
    }

    public Flowable<Item> getItems() {
        return getItems(getConfiguration().getLanguage());
    }

    public Single<DemoDetail> getDemoDetails(long matchId) {
        return testAndCall(DemoDetail[].class, "getdemodetails", Long.toString(matchId))
                .map(it -> it[0]);
    }

    public Flowable<LeagueLeaderboard> getLeagueLeaderboard(Queue queue, Division division, int season) {
        return testAndCall(LeagueLeaderboard[].class, "getleagueleaderboard", queue.getId().toString(), Integer.toString(division.ordinal()), Integer.toString(season))
                .flattenAsFlowable(Arrays::asList);
    }

    public Single<LeagueSeason[]> getLeagueSeasons(Queue queue) {
        return testAndCall(LeagueSeason[].class, "getleagueseasons", queue.getId().toString());
    }

    public Flowable<MatchDetail> getMatchDetails(long matchId) {
        return testAndCall(MatchDetail[].class, "getmatchdetails", Long.toString(matchId))
                .flattenAsFlowable(Arrays::asList);
    }

    public Flowable<MatchDetail> getMatchDetailsBatch(long... matchId) {
        if (matchId.length < 5) {
            return Flowable.error(new HiRezException("To get Batched matches you must defined minimum 5 matchId's"));
        }
        return testAndCall(MatchDetail[].class, "getmatchdetailsbatch", Arrays.stream(matchId).mapToObj(Long::toString).collect(Collectors.joining(",")))
                .flattenAsFlowable(Arrays::asList);
    }

    public Flowable<MatchHistory> getMatchHistory(long userId) {
        return testAndCall(MatchHistory[].class, "getmatchhistory", Long.toString(userId))
                .flattenAsFlowable(Arrays::asList);
    }

    public Flowable<MatchId> getMatchIdsByQueue(Queue queue, Date timestamp) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd/hh");
        DateFormat mdf = new SimpleDateFormat("mm");

        int minutes = Integer.parseInt(mdf.format(timestamp));

        minutes = minutes - minutes % 10 + ((minutes % 10) > 4 ? 10 : 0);

        return testAndCall(MatchId[].class, "getmatchidsbyqueue", queue.getId().toString(), df.format(timestamp) + minutes)
                .flattenAsFlowable(Arrays::asList);
    }

    public Flowable<LiveMatch> getMatchPlayerDetails(long matchId) {
        return testAndCall(LiveMatch[].class, "getmatchplayerdetails", Long.toString(matchId))
                .flattenAsFlowable(Arrays::asList);
    }

    public Flowable<QueueStat> getQueueStats(long userId, Queue queue) {
        return testAndCall(QueueStat[].class, "getqueuestats", Long.toString(userId), queue.getId().toString())
                .flattenAsFlowable(Arrays::asList);
    }

    public Flowable<ChampionRank> getChampionRanks(long userId) {
        return testAndCall(ChampionRank[].class, "getchampionranks", Long.toString(userId))
                .flattenAsFlowable(Arrays::asList);
    }

    public Flowable<Friend> getFriends(long userId) {
        return testAndCall(Friend[].class, "getfriends", Long.toString(userId))
                .flattenAsFlowable(Arrays::asList);
    }

    public Single<Player> getPlayer(long userId) {
        return testAndCall(Player[].class, "getplayer", Long.toString(userId))
                .map(it -> it[0]);
    }

    public Single<Player> getPlayer(long userId, Portal portal) {
        return testAndCall(Player[].class, "getplayer", Long.toString(userId), portal.getId().toString())
                .map(it -> it[0]);
    }

    public Flowable<PlayerIdPortal> getPlayerIdByName(String query) {
        return testAndCall(PlayerIdPortal[].class, "getplayeridbyname", query)
                .flattenAsFlowable(Arrays::asList);
    }

    public Flowable<PlayerIdPortal> getPlayerIdByPortalUserId(Portal portal, long id) {
        return testAndCall(PlayerIdPortal[].class, "getplayeridbyportaluserid", portal.getId().toString(), Long.toString(id))
                .flattenAsFlowable(Arrays::asList);
    }

    public Flowable<PlayerIdPortal> getPlayerIdsByGamerTag(Portal portal, String query) {
        return testAndCall(PlayerIdPortal[].class, "getplayeridsbygamertag", portal.getId().toString(), query)
                .flattenAsFlowable(Arrays::asList);
    }

    public Single<PlayerIdXboxSwitch[]> getPlayerIdInfoForXboxAndSwitch() {
        return testAndCall(PlayerIdXboxSwitch[].class, "getplayeridinfoforxboxandswitch", "stachu%20official");
    }

    public Flowable<PlayerLoadout> getPlayerLoadouts(String user, Language language) {
        return testAndCall(PlayerLoadout[].class, "getplayerloadouts", user, language.getId().toString())
                .flattenAsFlowable(Arrays::asList);
    }

    public Flowable<PlayerLoadout> getPlayerLoadouts(Long id) {
        return getPlayerLoadouts(id, getConfiguration().getLanguage());
    }

    public Flowable<PlayerLoadout> getPlayerLoadouts(String user) {
        return getPlayerLoadouts(user, getConfiguration().getLanguage());
    }

    public Flowable<PlayerLoadout> getPlayerLoadouts(Long id, Language language) {
        return getPlayerLoadouts(Long.toString(id), language);
    }

    public Single<PlayerStatus> getPlayerStatus(String user) {
        return testAndCall(PlayerStatus[].class, "getplayerstatus", user)
                .map(it -> it[0]);
    }

    public Single<PlayerStatus> getPlayerStatus(long id) {
        return getPlayerStatus(Long.toString(id));
    }

    public Single<ProLeagueDetail[]> getEsportsProLeagueDetails() {
        return testAndCall(ProLeagueDetail[].class, "getesportsproleaguedetails");
    }

    public Flowable<PlayerQuery> searchPlayer(String query) {
        return testAndCall(PlayerQuery[].class, "searchplayers", query)
                .flattenAsFlowable(Arrays::asList);
    }
}
