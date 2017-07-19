package pl.stachuofficial.hirezstudios;

import pl.stachuofficial.hirezstudios.instance.Language;
import pl.stachuofficial.util.StringData;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="damian@stachuofficial.pl">Damian Staszewski</a>
 * @since 1.8
 */
public class Smite extends HiRezGames {
    public enum Platform {
        /**
         * Smite PC API
         */
        PC("http://api.smitegame.com/smiteapi.svc"),
        /**
         * Smite XBOX One API
         */
        XBOX("http://api.xbox.smitegame.com/smiteapi.svc"),
        /**
         * Smite PlayStation 4 API
         */
        PS4("http://api.ps4.smitegame.com/smiteapi.svc");

        /**
         * API Url
         */
        final String url;

        /**
         * End points for API
         * @param url URL for API
         */
        Platform(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }

    public enum Queue {
        Conquest_5vs5(423),
        Novice_Queue(424),
        Conquest(426),
        Practice(427),
        Conquest_Challenge(429),
        Conquest_Ranked(430),
        Domination(433),
        MOTD(434), // (use with 465 to get all MOTD matches)
        Arena(435),
        Arena_Challenge(438),
        Domination_Challenge(439),
        Duel(440),
        Joust_Challenge(441),
        Assault(445),
        Assault_Challenge(446),
        Joust(448),
        Joust_Ranked(450),
        Conquest_Ranked2(451),
        Arena_Ranked(452),
        MOTD_List(465), // List of MOTD's (Supports “closing” the Queue by our platform; use with 434)
        Clash(466),
        Clash_Challenge(467);

        private int id;

        private static Map<Integer, Queue> map = new HashMap<Integer, Queue>();

        static {
            for(Queue queue : Queue.values()) {
                map.put(queue.id, queue);
            }
        }

        Queue(final int id) {
            this.id = id;
        }

        public int getId() { return id; }

        public static Queue valueOf(int id) {
            return map.get(id);
        }
    }

    /**
     * Smite API
     * @param devId Developer ID (DevId)
     * @param authKey Authorization Key (AuthKey)
     * @param platform Platform in {@link Smite.Platform}
     */
    public Smite(Smite.Platform platform, String devId, String authKey) {
        super(platform, devId, authKey);
    }

    /**
     * Getting God ranks by player
     * @param username This may either be:
     *                a.) the Player Name, or
     *                b.) the Hi-Rez internally stored player_id
     *                (available to API developers via the {@link #getPlayer(String)} API method).
     * @return Returns the Rank and Worshippers value for each God a player has played.
     */
    public StringData getGodRanks(String username) {
        return get("getgodranks", username);
    }

    /**
     * Getting Gods list.
     * @param language the language Id that you want results returned in. Check out {@link Language}. (Default {@link Language#English})
     * @return Returns all Gods and their various attributes.
     */
    public StringData getGods(Language language) {
        return get("getgods", String.valueOf(language.getId()));
    }
    /**
     * Getting god list. (Default {@link Language#English})
     * @return Returns all Gods and their various attributes.
     */
    public StringData getGods() {
        return getGods(Language.English);
    }

    /**
     * Getting gods leaderboard by queue
     * @param godId God id listed by {@link #getGods()}
     * @param queue the id of the game mode. See {@link Queue}
     * @return Returns the current season’s leaderboard for a god/queue combination.
     */
    public StringData getGodLeaderboard(String godId, Queue queue) {
        return get("getgodleaderboard", godId, String.valueOf(queue.getId()));
    }

    /**
     * Getting gods leaderboard by queue
     * @param godId God id listed by {@link #getGods()}
     * @param queue the id of the game mode. See {@link Queue}
     * @return Returns the current season’s leaderboard for a god/queue combination.
     */
    public StringData getGodLeaderboard(int godId, Queue queue) {
        return getGodLeaderboard(String.valueOf(godId), queue);
    }

    /**
     * Getting list skins of the God
     * @param godId God id listed by {@link #getGods()}
     * @param language the language Id that you want results returned in. Check out {@link Language}. (Default {@link Language#English})
     * @return Returns all available skins for a particular God.
     */
    public StringData getGodSkins(String godId, Language language) {
        return get("getgodskins", godId, String.valueOf(language.getId()));
    }

    /**
     * Getting list skins of the God
     * @param godId God id listed by {@link #getGods()}
     * @param language the language Id that you want results returned in. Check out {@link Language}. (Default {@link Language#English})
     * @return Returns all available skins for a particular God.
     */
    public StringData getGodSkins(int godId, Language language) {
        return getGodSkins(String.valueOf(godId), language);
    }

    /**
     * Getting list skins of the God. (Default {@link Language#English})
     * @param godId God id listed by {@link #getGods()}
     * @return Returns all available skins for a particular God.
     */
    public StringData getGodSkins(String godId) {
        return getGodSkins(godId, Language.English);
    }

    /**
     * Getting list skins of the God. (Default {@link Language#English})
     * @param godId God id listed by {@link #getGods()}
     * @return Returns all available skins for a particular God.
     */
    public StringData getGodSkins(int godId) {
        return getGodSkins(godId, Language.English);
    }

    /**
     * Getting list items recommended for God.
     * @param godId God id listed by {@link #getGods()}
     * @param language the language Id that you want results returned in. Check out {@link Language}. (Default {@link Language#English})
     * @return Returns the Recommended Items for a particular God.
     */
    public StringData getGodRecommendedItems(String godId, Language language) {
        return get("getgodrecommendeditems", godId, String.valueOf(language.getId()));
    }

    /**
     * Getting list items recommended for God.
     * @param godId God id listed by {@link #getGods()}
     * @param language the language Id that you want results returned in. Check out {@link Language}. (Default {@link Language#English})
     * @return Returns the Recommended Items for a particular God.
     */
    public StringData getGodRecommendedItems(int godId, Language language) {
        return getGodRecommendedItems(String.valueOf(godId), language);
    }

    /**
     * Getting list items recommended for God.
     * @param godId God id listed by {@link #getGods()}
     * @return Returns the Recommended Items for a particular God.
     */
    public StringData getGodRecommendedItems(String godId) {
        return getGodRecommendedItems(godId, Language.English);
    }

    /**
     * Getting list items recommended for God.
     * @param godId God id listed by {@link #getGods()}
     * @return Returns the Recommended Items for a particular God.
     */
    public StringData getGodRecommendedItems(int godId) {
        return getGodRecommendedItems(godId, Language.English);
    }
}
