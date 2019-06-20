package hirez.realm;

import hirez.api.*;
import hirez.api.object.*;
import hirez.api.object.interfaces.Queue;
import hirez.api.object.interfaces.ReturnedMessage;
import hirez.realm.object.*;
import io.reactivex.Flowable;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

@Slf4j
public class RealmRoyale {
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
    private static RestClient REST;

    public static void initConfig(ConfigurationBuilder configuration) {
        if (configuration.getBaseEndpoint() == null) {
            configuration.setBaseEndpoint(DEFAULT_BASE);
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
        return testAndCall(DataUsage.class, "getdataused");
    }

    public static Single<HiRezServer> getHiRezServerStatus() {
        return testAndCall(HiRezServer.class, "gethirezserverstatus");
    }

    public static Single<PatchInfo> getPatchInfo() {
        return testAndCall(PatchInfo.class, "getpatchinfo");
    }


    public static Flowable<Talent> getTalents(Language language) {
        return testAndCall(Talent[].class, "gettalents", language.getId().toString())
                .flattenAsFlowable(Arrays::asList);
    }

    public static Single<Leaderboard> getLeaderboard(Queue queue, Criteria criteria) {
        return testAndCall(Leaderboard.class, "getleaderboard", queue.getId().toString(), criteria.id.toString());
    }

    public static Single<MatchDetails> getMatchDetails(long matchId) {
        return testAndCall(MatchDetails.class, "getmatchdetails", "17327425");
    }

    public static Flowable<MatchId> getMatchIdsByQueue(Queue queue, Date timestamp) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd/hh");
        DateFormat mdf = new SimpleDateFormat("mm");

        int minutes = Integer.parseInt(mdf.format(timestamp));

        minutes = minutes - minutes % 10 + ((minutes % 10) > 4 ? 10 : 0);

        return testAndCall(MatchId[].class, "getmatchidsbyqueue", queue.getId().toString(), df.format(timestamp) + minutes)
                .flattenAsFlowable(Arrays::asList);
    }

    public static Single<PlayerMatchHistory> getPlayerMatchHistory(long userId) {
        return testAndCall(PlayerMatchHistory.class, "getplayermatchhistory", Long.toString(userId));
    }

    public static Single<PlayerMatchHistory> getPlayerMatchHistoryAfterDateTime(long userId, Date after) {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        df.setTimeZone(TimeZone.getTimeZone(ZoneOffset.UTC));
        return testAndCall(PlayerMatchHistory.class, "getplayermatchhistoryafterdatetime", df.format(after), Long.toString(userId));
    }

    public static Single<Player> getPlayer(long userId) {
        return testAndCall(Player.class, "getplayer", Long.toString(userId), "hirez");
    }

    public static Single<Player> getPlayerBySteamId(long steamId) {
        return testAndCall(Player.class, "getplayer", Long.toString(steamId), "steam");
    }

    public static Flowable<PlayerIdPortal> getPlayerIdByName(String query) {
        return testAndCall(PlayerIdPortal.class, "getplayeridbyname", query)
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

    public static Single<PlayerStats> getPlayerStats(long userId) {
        return testAndCall(PlayerStats.class, "getplayerstats", Long.toString(userId));
    }

    public static Single<PlayerStatus> getPlayerStatus(long userId) {
        return testAndCall(PlayerStatus.class, "getplayerstatus", Long.toString(userId));
    }

    public static Flowable<PlayerQuery> searchPlayer(String query) {
        return testAndCall(PlayerQuery[].class, "searchplayers", query)
                .flattenAsFlowable(Arrays::asList);
    }

    public static StatusPage getStatusPage() {
        return new StatusPage(REST);
    }
}
