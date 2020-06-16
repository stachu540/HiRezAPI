package hirez.realm;

import hirez.api.*;
import hirez.api.object.*;
import hirez.api.object.interfaces.Queue;
import hirez.api.object.interfaces.ReturnedMessage;
import hirez.realm.object.*;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;
import java.util.function.Consumer;

@Slf4j
public class RealmRoyale extends Endpoint {
    private static final BaseEndpoint DEFAULT_BASE = new BaseEndpoint() {
        @Override
        public Game getGame() {
            return new Game("35lh77mxwjy9", "Realm Royale");
        }

        @Override
        public Platform getPlatform() {
            return new Platform("4bsbxhr3mrlz", "PC");
        }

        @Override
        public String getBaseUrl() {
            return "https://api.realmroyale.com/realmapi.svc";
        }
    };

    private RealmRoyale(Configuration configuration) {
        super(configuration);
    }

    public static RealmRoyale create(Consumer<ConfigurationBuilder> configuration) {
        return new RealmRoyale(new ConfigurationBuilder().applyFrom((cfg) -> {
            configuration.accept(cfg);
            if (cfg.getBaseEndpoint() == null) {
                cfg.setBaseEndpoint(DEFAULT_BASE);
            }
        }).build());
    }

    public Flowable<Talent> getTalents(Language language) {
        return testAndCall(Talent[].class, "gettalents", language.getId().toString())
                .flattenAsFlowable(Arrays::asList);
    }

    public Single<Leaderboard> getLeaderboard(Queue queue, Criteria criteria) {
        return testAndCall(Leaderboard.class, "getleaderboard", queue.getId().toString(), criteria.id.toString());
    }

    public Single<MatchDetails> getMatchDetails(long matchId) {
        return testAndCall(MatchDetails.class, "getmatchdetails", "17327425");
    }

    public Flowable<MatchId> getMatchIdsByQueue(Queue queue, Date timestamp) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd/hh");
        DateFormat mdf = new SimpleDateFormat("mm");

        int minutes = Integer.parseInt(mdf.format(timestamp));

        minutes = minutes - minutes % 10 + ((minutes % 10) > 4 ? 10 : 0);

        return testAndCall(MatchId[].class, "getmatchidsbyqueue", queue.getId().toString(), df.format(timestamp) + minutes)
                .flattenAsFlowable(Arrays::asList);
    }

    public Single<PlayerMatchHistory> getPlayerMatchHistory(long userId) {
        return testAndCall(PlayerMatchHistory.class, "getplayermatchhistory", Long.toString(userId));
    }

    public Single<PlayerMatchHistory> getPlayerMatchHistoryAfterDateTime(long userId, Date after) {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        df.setTimeZone(TimeZone.getTimeZone(ZoneOffset.UTC));
        return testAndCall(PlayerMatchHistory.class, "getplayermatchhistoryafterdatetime", df.format(after), Long.toString(userId));
    }

    public Single<Player> getPlayer(long userId) {
        return testAndCall(Player.class, "getplayer", Long.toString(userId), "hirez");
    }

    public Single<Player> getPlayerBySteamId(long steamId) {
        return testAndCall(Player.class, "getplayer", Long.toString(steamId), "steam");
    }

    public Flowable<PlayerIdPortal> getPlayerIdByName(String query) {
        return testAndCall(PlayerIdPortal.class, "getplayeridbyname", query)
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

    public Single<PlayerStats> getPlayerStats(long userId) {
        return testAndCall(PlayerStats.class, "getplayerstats", Long.toString(userId));
    }

    public Single<PlayerStatus> getPlayerStatus(long userId) {
        return testAndCall(PlayerStatus.class, "getplayerstatus", Long.toString(userId));
    }

    public Flowable<PlayerQuery> searchPlayer(String query) {
        return testAndCall(PlayerQuery[].class, "searchplayers", query)
                .flattenAsFlowable(Arrays::asList);
    }
}
