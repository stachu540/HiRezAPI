package pl.stachuofficial.hirezstudios;

import pl.stachuofficial.hirezstudios.instance.Language;
import pl.stachuofficial.hirezstudios.instance.TierLeauge;
import pl.stachuofficial.util.HttpClient;
import pl.stachuofficial.util.StringData;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author <a href="damian@stachuofficial.pl">Damian Staszewski</a>
 * @since 1.8
 */
public class HiRezGames extends HttpClient {
    private final String devId;
    private final String authKey;
    private final Session session;

    HiRezGames(Smite.Platform platform, String devId, String authKey) {
        super(platform.url);
        this.devId = devId;
        this.authKey = authKey;
        this.session = new Session(platform, devId, authKey);
    }

    HiRezGames(Paladins.Platform platform, String devId, String authKey) {
        super(platform.url);
        this.devId = devId;
        this.authKey = authKey;
        this.session = new Session(platform, devId, authKey);
    }

    public StringData get(String endpoint, String... args) {
        return session.get(endpoint, args);
    }

    /**
     * pinging to API.
     * @return A quick way of validating access to the Hi-Rez API.
     */
    public String ping() {
        return request("pingJson").replace("\\", "");
    }
    /**
     * A required step to Authenticate the developerId/signature for further API use.
     */
    public void createSession() { this.session.generateSession(); }
    /**
     * Testing session when it is established.
     * @return A means of validating that a session is established.
     */
    public String testSession() { return this.session.testSession(); }

    /**
     * Checking server status.
     * @return Function returns UP/DOWN status for the primary game/platform environments. Data is cached once a minute.
     */
    public StringData getServerStatus() { return get("gethirezserverstatus"); }

    /**
     * Getting daily data usage
     * @return Returns API Developer daily usage limits and the current status against those limits.
     */
    public StringData getDataUsage() { return this.session.getDataUsage(); }

    /**
     * Rarely used in lieu of {@link #getMatchDetails(String)} )}.
     * @param match_id The id of the match. Can be obtained from <b>{@link #getMatchHistory(String)}</b>, <b>{@link #getTopMatches()}</b> &amp; <b>{@link #getMatchIDsByQueue}</b>.
     * @return Returns information regarding a particular match.
     */
    public StringData getDemoDetails(String match_id) {
        return get("getdemodetails", match_id);
    }

    /**
     * Rarely used in lieu of {@link #getMatchDetails(int)} )}.
     * @param match_id The id of the match. Can be obtained from <b>{@link #getMatchHistory(String)}</b>, <b>{@link #getTopMatches()}</b> &amp; <b>{@link #getMatchIDsByQueue}</b>.
     * @return Returns information regarding a particular match.
     */
    public StringData getDemoDetails(int match_id) {
        return getDemoDetails(String.valueOf(match_id));
    }

    /**
     * Getting E-Sport Machetes
     * @return  Returns the matchup information for each matchup for the current <b>eSports Pro League</b> season.
     *          An important return value is <blockquote>match_status</blockquote> which represents a match being <i>scheduled (<b>1</b>)</i>, <i>in-progress (<b>2</b>)</i>, or <i>complete (<b>3</b>)</i>.
     */
    public StringData getEsportsMatches() {
        return get("getesportsproleaguedetails");
    }

    /**
     * Getting friend list from player
     * @param username This may either be:
     *                a.) the Player Name, or
     *                b.) the Hi-Rez internally stored player_id
     *                (available to API developers via the {@link #getPlayer(String)} API method).
     * @return Returns the Smite User names of each of the player’s friends.
     */
    public StringData getFriends(String username) {
        return get("getfriends", username);
    }

    /**
     * Getting items with the stats and attributes.
     * @param lang the language Id that you want results returned in. Check out {@link Language}. (Default {@link Language#English})
     * @return Returns all Items and their various attributes.
     */
    public StringData getItems(Language lang) {
        return get("getitems", String.valueOf(lang.getId()));
    }
    /**
     * Getting items with the stats and attributes. (Default {@link Language#English})
     * @return Returns all Items and their various attributes.
     */
    public StringData getItems() {
        return getItems(Language.English);
    }

    /**
     * Getting match details.
     * @param match_id The id of the match. Can be obtained from <b>{@link #getMatchHistory(String)}</b>, <b>{@link #getTopMatches()}</b> &amp; <b>{@link #getMatchIDsByQueue}</b>.
     * @return Returns the statistics for a particular completed match.
     */
    public StringData getMatchDetails(String match_id) {
        return get("getmatchdetails", match_id);
    }

    /**
     * Getting match details.
     * @param match_id The id of the match. Can be obtained from <b>{@link #getMatchHistory(String)}</b>, <b>{@link #getTopMatches()}</b> &amp; <b>{@link #getMatchIDsByQueue}</b>.
     * @return Returns the statistics for a particular completed match.
     */
    public StringData getMatchDetails(int match_id) {
        return getMatchDetails(String.valueOf(match_id));
    }

    /**
     * Getting live match details.
     * @param match_id The id of the match. Can be obtained from <b>{@link #getMatchHistory(String)}</b>, <b>{@link #getTopMatches()}</b> &amp; <b>{@link #getMatchIDsByQueue}</b>.
     * @return Returns player information for a live match.
     */
    public StringData getMatchPlayingDetails(String match_id) {
        return get("getmatchplayerdetails", match_id);
    }

    /**
     * Getting live match details.
     * @param match_id The id of the match. Can be obtained from <b>{@link #getMatchHistory(String)}</b>, <b>{@link #getTopMatches()}</b> &amp; <b>{@link #getMatchIDsByQueue}</b>.
     * @return Returns player information for a live match.
     */
    public StringData getMatchPlayingDetails(int match_id) {
        return getMatchPlayingDetails(String.valueOf(match_id));
    }

    /**
     * Getting matches ID by Queue.
     * @param queue the id of the game mode. See {@link Smite.Queue}
     * @param date Date using by {@link Date} or similar.
     * @param allDay An parameter represents it would be the entire day, but be warned that this may be more data than we can return for certain queues.
     * @return Lists all Match IDs for a particular Match Queue; useful for API developers interested in constructing data by Queue.
     */
    public StringData getMatchIDsByQueue(Smite.Queue queue, Date date, boolean allDay) {
        SimpleDateFormat date_path = (allDay) ? new SimpleDateFormat("yyyyMMdd/-1") : new SimpleDateFormat("yyyyMMdd/HH");
        SimpleDateFormat minute = new SimpleDateFormat("mm");
        String min = String.valueOf(Integer.parseInt(minute.format(date)) - (Integer.parseInt(minute.format(date)) % 10));
        String path = date_path.format(date) + ((allDay) ? "," + min : "");
        return get("getmatchidsbyqueue", path);
    }

    /**
     * Getting matches ID by Queue.
     * @param queue the id of the game mode. See {@link Smite.Queue}
     * @param date Date using by {@link Date} or similar. Required to limiting data return.
     * @param allDay An parameter represents it would be the entire day, but be warned that this may be more data than we can return for certain queues.
     * @return Lists all Match IDs for a particular Match Queue; useful for API developers interested in constructing data by Queue.
     */
    public StringData getMatchIDsByQueue(Paladins.Queue queue, Date date, boolean allDay) {
        SimpleDateFormat date_path = (allDay) ? new SimpleDateFormat("yyyyMMdd/-1") : new SimpleDateFormat("yyyyMMdd/HH");
        SimpleDateFormat minute = new SimpleDateFormat("mm");
        String min = String.valueOf(Integer.parseInt(minute.format(date)) - (Integer.parseInt(minute.format(date)) % 10));
        String path = date_path.format(date) + ((allDay) ? "," + min : "");
        return get("getmatchidsbyqueue", path);
    }

    /**
     * Getting League Leaderboard
     * @param queue the id of the game mode. See {@link Smite.Queue}
     * @param tier League tier. See {@link TierLeauge}
     * @param season The season of a league. Starts at 1 and increases by 1 for each calendar month
     * @return Returns the top players for a particular league (as indicated by the queue/tier/season parameters).
     */
    public StringData getLeagueLeaderboard(Smite.Queue queue, TierLeauge tier, String season) {
        return get("getleagueleaderboard", String.valueOf(queue.getId()), String.valueOf(tier.getId()), season);
    }

    /**
     * Getting League Leaderboard
     * @param queue the id of the game mode. See {@link Smite.Queue}
     * @param tier League tier. See {@link TierLeauge}
     * @param season The season of a league. Starts at 1 and increases by 1 for each calendar month
     * @return Returns the top players for a particular league (as indicated by the queue/tier/season parameters).
     */
    public StringData getLeagueLeaderboard(Paladins.Queue queue, TierLeauge tier, String season) {
        return get("getleagueleaderboard", String.valueOf(queue.getId()), String.valueOf(tier.getId()), season);
    }

    /**
     * Getting League Leaderboard
     * @param queue the id of the game mode. See {@link Smite.Queue}
     * @param tier League tier. See {@link TierLeauge}
     * @param season The season of a league. Starts at 1 and increases by 1 for each calendar month
     * @return Returns the top players for a particular league (as indicated by the queue/tier/season parameters).
     */
    public StringData getLeagueLeaderboard(Smite.Queue queue, TierLeauge tier, int season) {
        return getLeagueLeaderboard(queue, tier, String.valueOf(season));
    }

    /**
     * Getting League Leaderboard
     * @param queue the id of the game mode. See {@link Smite.Queue}
     * @param tier League tier. See {@link TierLeauge}
     * @param season The season of a league. Starts at 1 and increases by 1 for each calendar month
     * @return Returns the top players for a particular league (as indicated by the queue/tier/season parameters).
     */
    public StringData getLeagueLeaderboard(Paladins.Queue queue, TierLeauge tier, int season) {
        return getLeagueLeaderboard(queue, tier, String.valueOf(season));
    }

    /**
     * Getting list of League Seasons.
     * @param queue the id of the game mode. See {@link Smite.Queue}
     * @return Provides a list of seasons (including the single active season) for a match queue.
     */
    public StringData getLeagueSeasons(Smite.Queue queue) {
        return get("getleagueseasons", String.valueOf(queue.getId()));
    }

    /**
     * Getting list of League Seasons.
     * @param queue the id of the game mode. See {@link Paladins.Queue}
     * @return Provides a list of seasons (including the single active season) for a match queue.
     */
    public StringData getLeagueSeasons(Paladins.Queue queue) {
        return get("getleagueseasons", String.valueOf(queue.getId()));
    }

    /**
     * Gets recent matches and high level match statistics for a particular player.
     * @param username This may either be:
     *                a.) the Player Name, or
     *                b.) the Hi-Rez internally stored player_id
     *                (available to API developers via the {@link #getPlayer(String)} API method).
     * @return Returns recent matches and high level match statistics for a particular player.
     */
    public StringData getMatchHistory(String username) {
        return get("getmatchhistory", username);
    }

    /**
     * Get Matches of the Day
     * @return Returns information about the 20 most recent Match-of-the-Days.
     */
    public StringData getMotd() {
        return get("getmotd");
    }

    /**
     * Get player information
     * @param username This may either be:
     *                a.) the Player Name, or
     *                b.) the Hi-Rez internally stored player_id
     *                (available to API developers via the {@link #getPlayer(String)} API method).
     * @return Returns league and other high level data for a particular player.
     */
    public StringData getPlayer(String username) {
        return get("getplayer", username);
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
    public StringData getPlayerStatus(String username) {
        return get("getplayerstatus", username);
    }

    /**
     *
     * @param username This may either be:
     *                a.) the Player Name, or
     *                b.) the Hi-Rez internally stored player_id
     *                (available to API developers via the {@link #getPlayer(String)} API method).
     * @param queue the id of the game mode. See {@link Smite.Queue}
     * @return Returns match summary statistics for a (player, queue) combination grouped by gods played.
     */
    public StringData getQueueStats(String username, Smite.Queue queue) {
        return get("getqueuestats", username, String.valueOf(queue.getId()));
    }

    /**
     * Getting top matches.
     * @return Lists the 50 most watched / most recent recorded matches.
     */
    public StringData getTopMatches() {
        return get("gettopmatches");
    }

    /**
     * getting player achievements
     * @param username This may either be:
     *                a.) the Player Name, or
     *                b.) the Hi-Rez internally stored player_id
     *                (available to API developers via the {@link #getPlayer(String)} API method).
     * @return Returns select achievement totals (Double kills, Tower Kills, First Bloods, etc) for the specified playerId.
     */
    public StringData getPlayerAchievements(String username) {
        int player_id = getPlayer(username).toJsonArray().getJSONObject(0).getInt("Id");
        return getPlayerAchievements(player_id);
    }

    /**
     * getting player achievements
     * @param playerId This is the Player ID generated by {@link #getPlayer(String)}
     * @return Returns select achievement totals (Double kills, Tower Kills, First Bloods, etc) for the specified playerId.
     */
    public StringData getPlayerAchievements(int playerId) {
        return get("getplayerachievements", String.valueOf(playerId));
    }

    /**
     * Getting information about current deployed patch
     * @return Function returns information about current deployed patch. Currently, this information only includes patch version.
     */
    public StringData getPatchInfo() {
        return get("getpatchinfo");
    }
}
