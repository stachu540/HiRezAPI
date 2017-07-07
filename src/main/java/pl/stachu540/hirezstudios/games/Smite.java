package pl.stachu540.hirezstudios.games;

import org.json.JSONObject;
import pl.stachu540.hirezstudios.instance.Language;
import pl.stachu540.hirezstudios.instance.Queue;

/**
 * @author Damian Staszewski <damian@stachuofficial.pl>
 * @since 1.8
 */
public class Smite extends HiRezGames {

    public enum Endpoint {
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
        private final String url;

        /**
         * End points for API
         * @param url URL for API
         */
        Endpoint(String url) {
            this.url = url;
        }

        /**
         * Getting URL
         * @return API URL
         */
        public String getUrl() { return url; }
    }

    /**
     * Smite API
     * @param devId Developer ID (DevId)
     * @param authKey Authorization Key (AuthKey)
     * @param endpoint Endpoint platform for API
     */
    public Smite(String devId, String authKey, Smite.Endpoint endpoint) {
        super(devId, authKey, endpoint);
    }


    /**
     * Change platform
     * @param endpoint Endpoint
     */
    public void setPlatform(Smite.Endpoint endpoint) {
        super.setUrl(endpoint.getUrl());
    }
    /**
     * Getting God ranks by player
     * @param username This may either be:
     *                a.) the Player Name, or
     *                b.) the Hi-Rez internally stored player_id
     *                (available to API developers via the {@link #getPlayer(String)} API method).
     * @return Returns the Rank and Worshippers value for each God a player has played.
     */
    public JSONObject getGodRanks(String username) {
        return catchData("getgodranks", username);
    }

    /**
     * Getting Gods list.
     * @param language the language Id that you want results returned in. Check out {@link Language}. (Default {@value Language#English})
     * @return Returns all Gods and their various attributes.
     */
    public JSONObject getGods(Language language) {
        return catchData("getgods", String.valueOf(language.getId()));
    }
    /**
     * Getting god list. (Default {@value Language#English})
     * @return Returns all Gods and their various attributes.
     */
    public JSONObject getGods() {
        return getGods(Language.English);
    }

    /**
     * Getting gods leaderboard by queue
     * @param godId God id listed by {@link #getGods()}
     * @param queue the id of the game mode. See {@link Queue}
     * @return Returns the current season’s leaderboard for a god/queue combination.
     */
    public JSONObject getGodLeaderboard(String godId, Queue queue) {
        return catchData("getgodleaderboard", godId, String.valueOf(queue.getId()));
    }

    /**
     * Getting gods leaderboard by queue
     * @param godId God id listed by {@link #getGods()}
     * @param queue the id of the game mode. See {@link Queue}
     * @return Returns the current season’s leaderboard for a god/queue combination.
     */
    public JSONObject getGodLeaderboard(int godId, Queue queue) {
        return getGodLeaderboard(String.valueOf(godId), queue);
    }

    /**
     * Getting list skins of the God
     * @param godId God id listed by {@link #getGods()}
     * @param language the language Id that you want results returned in. Check out {@link Language}. (Default {@value Language#English})
     * @return Returns all available skins for a particular God.
     */
    public JSONObject getGodSkins(String godId, Language language) {
        return catchData("getgodskins", godId, String.valueOf(language.getId()));
    }

    /**
     * Getting list skins of the God
     * @param godId God id listed by {@link #getGods()}
     * @param language the language Id that you want results returned in. Check out {@link Language}. (Default {@value Language#English})
     * @return Returns all available skins for a particular God.
     */
    public JSONObject getGodSkins(int godId, Language language) {
        return getGodSkins(String.valueOf(godId), language);
    }

    /**
     * Getting list skins of the God. (Default {@value Language#English})
     * @param godId God id listed by {@link #getGods()}
     * @return Returns all available skins for a particular God.
     */
    public JSONObject getGodSkins(String godId) {
        return getGodSkins(godId, Language.English);
    }

    /**
     * Getting list skins of the God. (Default {@value Language#English})
     * @param godId God id listed by {@link #getGods()}
     * @return Returns all available skins for a particular God.
     */
    public JSONObject getGodSkins(int godId) {
        return getGodSkins(godId, Language.English);
    }

    /**
     * Getting list items recommended for God.
     * @param godId God id listed by {@link #getGods()}
     * @param language the language Id that you want results returned in. Check out {@link Language}. (Default {@value Language#English})
     * @return Returns the Recommended Items for a particular God.
     */
    public JSONObject getGodRecommendedItems(String godId, Language language) {
        return catchData("getgodrecommendeditems", godId, String.valueOf(language.getId()));
    }

    /**
     * Getting list items recommended for God.
     * @param godId God id listed by {@link #getGods()}
     * @param language the language Id that you want results returned in. Check out {@link Language}. (Default {@value Language#English})
     * @return Returns the Recommended Items for a particular God.
     */
    public JSONObject getGodRecommendedItems(int godId, Language language) {
        return getGodRecommendedItems(String.valueOf(godId), language);
    }

    /**
     * Getting list items recommended for God.
     * @param godId God id listed by {@link #getGods()}
     * @return Returns the Recommended Items for a particular God.
     */
    public JSONObject getGodRecommendedItems(String godId) {
        return getGodRecommendedItems(godId, Language.English);
    }

    /**
     * Getting list items recommended for God.
     * @param godId God id listed by {@link #getGods()}
     * @return Returns the Recommended Items for a particular God.
     */
    public JSONObject getGodRecommendedItems(int godId) {
        return getGodRecommendedItems(godId, Language.English);
    }
}
