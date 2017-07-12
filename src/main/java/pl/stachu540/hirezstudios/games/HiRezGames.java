package pl.stachu540.hirezstudios.games;

import com.sun.istack.internal.NotNull;
import org.json.JSONObject;
import pl.stachu540.hirezstudios.instance.Language;
import pl.stachu540.hirezstudios.instance.Queue;
import pl.stachu540.hirezstudios.instance.TierLeauge;
import pl.stachu540.hirezstudios.util.Signature;
import pl.stachu540.util.API;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.SimpleTimeZone;

/**
 * @author Damian Staszewski <damian@stachuofficial.pl>
 * @since 1.8
 */
class HiRezGames extends API {

    private final String devId;
    private final String authKey;
    private String sessionId = "";

    /**
     * Hi-Rez Studios API for Smite and Paladins games
     * @param devId Developer ID (DevId)
     * @param authKey Authorization Key (AuthKey)
     * @param endpoint Endpoint
     */
    HiRezGames(@NotNull String devId, @NotNull String authKey, @NotNull Smite.Endpoint endpoint) {
        super(endpoint.getUrl());
        this.devId = devId;
        this.authKey = authKey;
    }

    /**
     * Hi-Rez Studios API for Smite and Paladins games
     * @param devId Developer ID (DevId)
     * @param authKey Authorization Key (AuthKey)
     * @param endpoint Endpoint
     */
    HiRezGames(@NotNull String devId, @NotNull String authKey, @NotNull Paladins.Endpoint endpoint) {
        super(endpoint.getUrl());
        this.devId = devId;
        this.authKey = authKey;
    }

    /**
     * Generating current timestamp
     * @return Timestamps formatted into <b>yyyyMMddHHmmss</b>
     */
    private String getTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sdf.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));
        return sdf.format(new Date());
    }

    /**
     * Generating signature for each API query
     * @param method endpoint method name
     * @param timestamp timestamp generated by {@link #getTimestamp()} method
     * @return MD5 Hash coded signature
     */
    private String getSignature(String method, String timestamp) {
        Signature signature = new Signature(devId, authKey);
        return signature.generateSignature(method, timestamp).getHash().toLowerCase();
    }

    /**
     * Getting data query
     * @param endpoint endpoint method name
     * @param args arguments in the correct order
     * @return data object in {@link JSONObject}
     */
    // TODO: testSession() is looping while he throwing StackOverflowError exception
    JSONObject catchData(String endpoint, String... args) {
        if (!sessionId.isEmpty()) {
            JSONObject test = testSession();
            if (test.getJSONObject("data").getInt("responseCode") != 200) {
                createSession();
            }
        } else createSession();
        String timestamp = getTimestamp();
        String signature = getSignature(endpoint, timestamp);
        return getData(requestType.GET, "/" + endpoint + "Json/" + devId + "/" + signature + "/" + sessionId + "/" + timestamp + ((Arrays.asList(args).toArray().length > 0) ? "/" + String.join("/", Arrays.asList(args)) : "" ));
    }

    /**
     * A required step to Authenticate the developerId/signature for further API use.
     */
    private void createSession() {
        String timestamp = getTimestamp();
        String signature = getSignature("createsession", timestamp);
        JSONObject ses = getData(requestType.GET, "/createsessionJson/" + devId + "/" + signature + "/" + timestamp);
        if (
                ses.getJSONObject("data").getInt("responseCode") == 200 &&
                ses.getJSONObject("content").getString("ret_msg").equals("Approved")
           ) sessionId = ses.getJSONObject("content").getString("session_id");
    }

    /**
     * A means of validating that a session is established.
     * @return
     */
    public JSONObject testSession() {
        return catchData("testsession");
    }

    /**
     * A quick way of validating access to the Hi-Rez API.
     * @return
     */
    public JSONObject ping() {
        return getData(requestType.GET, "/pingJson");
    }

    /**
     * Getting server status
     * @return Function returns UP/DOWN status for the primary game/platform environments. Data is cached once a minute.
     */
    public JSONObject getServerStatus() {
        return catchData("gethirezserverstatus");
    }

    /**
     * Getting daily data usage
     * @return Returns API Developer daily usage limits and the current status against those limits.
     */
    public JSONObject getDataUsage() {
        return catchData("getdataused");
    }

    /**
     * Rarely used in lieu of {@link #getMatchDetails(String)} )}.
     * @param match_id The id of the match. Can be obtained from <b>{@link #getMatchHistory(String)}</b>, <b>{@link #getTopMatches()}</b> & <b>{@link #getMatchIDsByQueue}</b>.
     * @return Returns information regarding a particular match.
     */
    public JSONObject getDemoDetails(String match_id) {
        return catchData("getdemodetails", match_id);
    }

    /**
     * Rarely used in lieu of {@link #getMatchDetails(int)} )}.
     * @param match_id The id of the match. Can be obtained from <b>{@link #getMatchHistory(String)}</b>, <b>{@link #getTopMatches()}</b> & <b>{@link #getMatchIDsByQueue}</b>.
     * @return Returns information regarding a particular match.
     */
    public JSONObject getDemoDetails(int match_id) {
        return getDemoDetails(String.valueOf(match_id));
    }

    /**
     * Getting E-Sport Machetes
     * @return  Returns the matchup information for each matchup for the current <b>eSports Pro League</b> season.
     *          An important return value is <blockquote>match_status</blockquote> which represents a match being <i>scheduled (<b>1</b>)</i>, <i>in-progress (<b>2</b>)</i>, or <i>complete (<b>3</b>)</i>.
     */
    public JSONObject getEsportsMatches() {
        return catchData("getesportsproleaguedetails");
    }

    /**
     * Getting friend list from player
     * @param username This may either be:
     *                a.) the Player Name, or
     *                b.) the Hi-Rez internally stored player_id
     *                (available to API developers via the {@link #getPlayer(String)} API method).
     * @return Returns the Smite User names of each of the player’s friends.
     */
    public JSONObject getFriends(String username) {
        return catchData("getfriends", username);
    }

    /**
     * Getting items with the stats and attributes.
     * @param lang the language Id that you want results returned in. Check out {@link Language}. (Default {@value Language#English})
     * @return Returns all Items and their various attributes.
     */
    public JSONObject getItems(Language lang) {
        return catchData("getitems", String.valueOf(lang.getId()));
    }
    /**
     * Getting items with the stats and attributes. (Default {@value Language#English})
     * @return Returns all Items and their various attributes.
     */
    public JSONObject getItems() {
        return getItems(Language.English);
    }

    /**
     * Getting match details.
     * @param match_id The id of the match. Can be obtained from <b>{@link #getMatchHistory(String)}</b>, <b>{@link #getTopMatches()}</b> & <b>{@link #getMatchIDsByQueue}</b>.
     * @return Returns the statistics for a particular completed match.
     */
    public JSONObject getMatchDetails(String match_id) {
        return catchData("getmatchdetails", match_id);
    }

    /**
     * Getting match details.
     * @param match_id The id of the match. Can be obtained from <b>{@link #getMatchHistory(String)}</b>, <b>{@link #getTopMatches()}</b> & <b>{@link #getMatchIDsByQueue}</b>.
     * @return Returns the statistics for a particular completed match.
     */
    public JSONObject getMatchDetails(int match_id) {
        return getMatchDetails(String.valueOf(match_id));
    }

    /**
     * Getting live match details.
     * @param match_id The id of the match. Can be obtained from <b>{@link #getMatchHistory(String)}</b>, <b>{@link #getTopMatches()}</b> & <b>{@link #getMatchIDsByQueue}</b>.
     * @return Returns player information for a live match.
     */
    public JSONObject getMatchPlayingDetails(String match_id) {
        return catchData("getmatchplayerdetails", match_id);
    }

    /**
     * Getting live match details.
     * @param match_id The id of the match. Can be obtained from <b>{@link #getMatchHistory(String)}</b>, <b>{@link #getTopMatches()}</b> & <b>{@link #getMatchIDsByQueue}</b>.
     * @return Returns player information for a live match.
     */
    public JSONObject getMatchPlayingDetails(int match_id) {
        return getMatchPlayingDetails(String.valueOf(match_id));
    }
// TODO: Match ID by Queue timestamps
//    public JSONObject getMatchIDsByQueue() {
//        return catchData("getmatchidsbyqueue")
//    }

    /**
     * Returns the top players for a particular league (as indicated by the queue/tier/season parameters).
     * @param queue the id of the game mode. See {@link Queue}
     * @param tier League tier. See {@link TierLeauge}
     * @param season The season of a league. Starts at 1 and increases by 1 for each calendar month
     * @return
     */
    public JSONObject getLeagueLeaderboard(Queue queue, TierLeauge tier, String season) {
        return catchData("getleagueleaderboard", String.valueOf(queue.getId()), String.valueOf(tier.getId()), season);
    }

    /**
     * Returns the top players for a particular league (as indicated by the queue/tier/season parameters).
     * @param queue the id of the game mode. See {@link Queue}
     * @param tier League tier. See {@link TierLeauge}
     * @param season The season of a league. Starts at 1 and increases by 1 for each calendar month
     * @return
     */
    public JSONObject getLeagueLeaderboard(Queue queue, TierLeauge tier, int season) {
        return getLeagueLeaderboard(queue, tier, String.valueOf(season));
    }

    /**
     * Getting list of League Seasons.
     * @param queue the id of the game mode. See {@link Queue}
     * @return Provides a list of seasons (including the single active season) for a match queue.
     */
    public JSONObject getLeagueSeasons(Queue queue) {
        return catchData("getleagueseasons", String.valueOf(queue.getId()));
    }

    /**
     * Gets recent matches and high level match statistics for a particular player.
     * @param username This may either be:
     *                a.) the Player Name, or
     *                b.) the Hi-Rez internally stored player_id
     *                (available to API developers via the {@link #getPlayer(String)} API method).
     * @return Returns recent matches and high level match statistics for a particular player.
     */
    public JSONObject getMatchHistory(String username) {
        return catchData("getmatchhistory", username);
    }

    /**
     * Get Matches of the Day
     * @return Returns information about the 20 most recent Match-of-the-Days.
     */
    public JSONObject getMotd() {
        return catchData("getmotd");
    }

    /**
     * Get player information
     * @param username This may either be:
     *                a.) the Player Name, or
     *                b.) the Hi-Rez internally stored player_id
     *                (available to API developers via the {@link #getPlayer(String)} API method).
     * @return Returns league and other high level data for a particular player.
     */
    public JSONObject getPlayer(String username) {
        return catchData("getplayer", username);
    }

    /**
     * Getting player status in the game
     * @param username This may either be:
     *                a.) the Player Name, or
     *                b.) the Hi-Rez internally stored player_id
     *                (available to API developers via the {@link #getPlayer(String)} API method).
     * @return Returns player status as follows:
     *      <blockquote>0</blockquote> - Offline
     *      <blockquote>1</blockquote> - In Lobby (basically anywhere except god selection or in game)
     *      <blockquote>2</blockquote> - god Selection (player has accepted match and is selecting god before start of game)
     *      <blockquote>3</blockquote> - In Game (match has started)
     *      <blockquote>4</blockquote> - Online (player is logged in, but may be blocking broadcast of player state)
     *      <blockquote>5</blockquote> - Unknown (player not found)
     */
    public JSONObject getPlayerStatus(String username) {
        return catchData("getplayerstatus", username);
    }

    /**
     *
     * @param username This may either be:
     *                a.) the Player Name, or
     *                b.) the Hi-Rez internally stored player_id
     *                (available to API developers via the {@link #getPlayer(String)} API method).
     * @param queue the id of the game mode. See {@link Queue}
     * @return Returns match summary statistics for a (player, queue) combination grouped by gods played.
     */
    public JSONObject getQueueStats(String username, Queue queue) {
        return catchData("getqueuestats", username, String.valueOf(queue.getId()));
    }

    /**
     * Getting top matches.
     * @return Lists the 50 most watched / most recent recorded matches.
     */
    public JSONObject getTopMatches() {
        return catchData("gettopmatches");
    }

//    public JSONObject getPlayerAchievements(String username) {
//
//    }

    /**
     * Getting information about current deployed patch
     * @return Function returns information about current deployed patch. Currently, this information only includes patch version.
     */
    public JSONObject getPatchInfo() {
        return catchData("getpatchinfo");
    }
}
