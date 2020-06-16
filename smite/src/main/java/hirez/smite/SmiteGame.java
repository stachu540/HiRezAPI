package hirez.smite;

import hirez.api.*;
import hirez.api.object.*;
import hirez.api.object.interfaces.Queue;
import hirez.api.object.interfaces.ReturnedMessage;
import hirez.smite.object.*;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class SmiteGame extends Endpoint {

    private SmiteGame(Configuration configuration) {
        super(configuration);
    }

    public static SmiteGame create(Consumer<ConfigurationBuilder> configuration) {
        return new SmiteGame(new ConfigurationBuilder().applyFrom((cfg) -> {
            configuration.accept(cfg);
            if (cfg.getBaseEndpoint() == null) {
                cfg.setBaseEndpoint(SmitePlatform.PC);
            }
        }).build());
    }

    public Flowable<GodLeaderboard> getGodLeaderboard(long godId, Queue queue) {
        return testAndCall(GodLeaderboard[].class, "getgodleaderboard", Long.toString(godId), queue.getId().toString())
                .flattenAsFlowable(Arrays::asList);
    }

    public Flowable<ItemRecommendation> getGodRecommendedItems(long godId, Language language) {
        return testAndCall(ItemRecommendation[].class, "getgodrecommendeditems", Long.toString(godId), language.getId().toString())
                .flattenAsFlowable(Arrays::asList);
    }

    public Flowable<ItemRecommendation> getGodRecommendedItems(long godId) {
        return getGodRecommendedItems(godId, getConfiguration().getLanguage());
    }

    public Flowable<God> getGods(Language language) {
        return testAndCall(God[].class, "getgods", language.getId().toString())
                .flattenAsFlowable(Arrays::asList);
    }

    public Flowable<God> getGods() {
        return getGods(getConfiguration().getLanguage());
    }

    public Flowable<GodSkin> getGodSkins(long godId, Language language) {
        return testAndCall(GodSkin[].class, "getgodskins", Long.toString(godId), language.getId().toString())
                .flattenAsFlowable(Arrays::asList);
    }

    public Flowable<GodSkin> getGodSkins(long godId) {
        return getGodSkins(godId, getConfiguration().getLanguage());
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
        DateFormat df = new SimpleDateFormat("yyyyMMdd/HH");
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

    public Flowable<TopMatch> getTopMatches() {
        return testAndCall(TopMatch[].class, "gettopmatches")
                .flattenAsFlowable(Arrays::asList);
    }

    public Flowable<Friend> getFriends(long userId) {
        return testAndCall(Friend[].class, "getfriends", Long.toString(userId))
                .flattenAsFlowable(Arrays::asList);
    }

    public Flowable<GodRank> getGodRanks(long userId) {
        return testAndCall(GodRank[].class, "getgodranks", Long.toString(userId))
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

    public Single<Achievements> getPlayerAchievements(long userId) {
        return testAndCall(Achievements.class, "getplayerachievements", Long.toString(userId));
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

    public Single<PlayerStatus> getPlayerStatus(long userId) {
        return testAndCall(PlayerStatus[].class, "getplayerstatus", Long.toString(userId))
                .map(it -> it[0]);
    }

    public Single<Team> getTeamDetails(long teamId) {
        return testAndCall(Team[].class, "getteamdetails", Long.toString(teamId))
                .map(it -> it[0]);
    }

    public Flowable<TeamMember> getTeamPlayers(long teamId) {
        return testAndCall(TeamMember[].class, "getteamplayers", Long.toString(teamId))
                .flattenAsFlowable(Arrays::asList);
    }

    public Flowable<TeamQuery> searchTeams(String query) {
        return testAndCall(TeamQuery[].class, "searchteams", query)
                .flattenAsFlowable(Arrays::asList);
    }

    public Flowable<ProLeagueDetail> getEsportsProLeagueDetails() {
        return testAndCall(ProLeagueDetail[].class, "getesportsproleaguedetails")
                .flattenAsFlowable(Arrays::asList);
    }

    public Flowable<MatchOfTheDay> getMOTD() {
        return testAndCall(MatchOfTheDay[].class, "getmotd")
                .flattenAsFlowable(Arrays::asList);
    }

    public Flowable<PlayerQuery> searchPlayer(String query) {
        return testAndCall(PlayerQuery[].class, "searchplayers", query)
                .flattenAsFlowable(Arrays::asList);
    }

    static boolean isRanked(int id) {
        return Arrays.asList(440, 450, 451, 502, 503, 504).contains(id);
    }
}
