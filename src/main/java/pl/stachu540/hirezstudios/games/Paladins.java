package pl.stachu540.hirezstudios.games;

import org.json.JSONObject;
import pl.stachu540.hirezstudios.instance.Language;

/**
 * @author Damian Staszewski <damian@stachuofficial.pl>
 * @since 1.8
 */
public class Paladins extends HiRezGames {

    public enum Endpoint {
        /**
         * Paladins PC API
         */
        PC("http://api.paladins.com/paladinsapi.svc"),
        /**
         * Paladins XBOX One API
         */
        XBOX("http://api.xbox.paladins.com/paladinsapi.svc"),
        /**
         * Paladins PlayStation 4 API
         */
        PS4("http://api.ps4.paladins.com/paladinsapi.svc");

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
     * Paladins API
     * @param devId Developer ID (DevId)
     * @param authKey Authorization Key (AuthKey)
     * @param endpoint Endpoint platform for API
     */
    public Paladins(String devId, String authKey, Paladins.Endpoint endpoint) {
        super(devId, authKey, endpoint);
    }

    /**
     * Change platform
     * @param endpoint Endpoint
     */
    public void setPlatform(Paladins.Endpoint endpoint) {
        super.setUrl(endpoint.getUrl());
    }
    /**
     * Getting Champion ranks by player
     * @param username This may either be:
     *                a.) the Player Name, or
     *                b.) the Hi-Rez internally stored player_id
     *                (available to API developers via the {@link #getPlayer(String)} API method).
     * @return Returns the Rank and Worshippers value for each Champion a player has played.
     */
    public JSONObject getChampionRanks(String username) {
        return catchData("getchampionranks", username);
    }

    /**
     * Getting Champions list.
     * @param language the language Id that you want results returned in. Check out {@link Language}. (Default {@value Language#English})
     * @return Returns all Champions and their various attributes.
     */
    public JSONObject getChampions(Language language) {
        return catchData("getchampions", String.valueOf(language.getId()));
    }

    /**
     * Getting Champions list. (Default {@value Language#English})
     * @return Returns all Champions and their various attributes.
     */
    public JSONObject getChampions() {
        return getChampions(Language.English);
    }

    /**
     * Getting list skins of the Champion
     * @param champId God id listed by {@link #getChampions()}
     * @param language the language Id that you want results returned in. Check out {@link Language}. (Default {@value Language#English})
     * @return Returns all available skins for a particular Champion.
     */
    public JSONObject getChampionSkins(String champId, Language language) {
        return catchData("getchampionskins", champId, String.valueOf(language.getId()));
    }

    /**
     * Getting list skins of the Champion
     * @param champId God id listed by {@link #getChampions()}
     * @param language the language Id that you want results returned in. Check out {@link Language}. (Default {@value Language#English})
     * @return Returns all available skins for a particular Champion.
     */
    public JSONObject getChampionSkins(int champId, Language language) {
        return getChampionSkins(String.valueOf(champId), language);
    }

    /**
     * Getting list skins of the Champion. (Default {@value Language#English})
     * @param champId God id listed by {@link #getChampions()}
     * @return Returns all available skins for a particular Champion.
     */
    public JSONObject getChampionSkins(String champId) {
        return getChampionSkins(champId, Language.English);
    }

    /**
     * Getting list skins of the Champion. (Default {@value Language#English})
     * @param champId God id listed by {@link #getChampions()}
     * @return Returns all available skins for a particular Champion.
     */
    public JSONObject getChampionSkins(int champId) {
        return getChampionSkins(champId, Language.English);
    }

    /**
     * Getting list items recommended for Champion.
     * @param champId God id listed by {@link #getChampions()}
     * @param language the language Id that you want results returned in. Check out {@link Language}. (Default {@value Language#English})
     * @return Returns the Recommended Items for a particular Champion.
     */
    public JSONObject getChampionRecommendedItems(String champId, Language language) { return catchData("getchampionecommendeditems", champId, String.valueOf(language.getId())); }

    /**
     * Getting list items recommended for Champion.
     * @param champId God id listed by {@link #getChampions()}
     * @param language the language Id that you want results returned in. Check out {@link Language}. (Default {@value Language#English})
     * @return Returns the Recommended Items for a particular Champion.
     */
    public JSONObject getChampionRecommendedItems(int champId, Language language) { return getChampionRecommendedItems(String.valueOf(champId), language);}

    /**
     * Getting list items recommended for Champion.
     * @param champId God id listed by {@link #getChampions()}
     * @return Returns the Recommended Items for a particular Champion.
     */
    public JSONObject getChampionRecommendedItems(String champId) { return getChampionRecommendedItems(champId, Language.English); }

    /**
     * Getting list items recommended for Champion.
     * @param champId God id listed by {@link #getChampions()}
     * @return Returns the Recommended Items for a particular Champion.
     */
    public JSONObject getChampionRecommendedItems(int champId) { return getChampionRecommendedItems(champId, Language.English); }


}
