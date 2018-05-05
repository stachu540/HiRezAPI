package hirezapi.endpoints;

import hirezapi.HiRezApi;
import hirezapi.json.Achievements;
import hirezapi.json.Player;
import hirezapi.json.PlayerStatus;

public class UserEndpoint extends AbstractEndpoint {
    public UserEndpoint(HiRezApi api) {
        super(api);
    }

    /**
     * Getting Player Information
     * @param user In Game Name or User ID
     * @return player information
     */
    public Player getPlayer(String user) {
        return api.getRestClient().getForObject(buildUrl("getplayer", user, "UTF-8"), Player[].class)[0];
    }

    public Achievements getPlayerAchievments(long id) {
        return api.getRestClient().getForObject(buildUrl("getplayerachievements", Long.toString(id)), Achievements.class);
    }

    /**
     * Getting Player Information
     * @param user In Game Name or User ID
     * @return player information
     */
    public PlayerStatus getPlayerStatus(String user) {
        return api.getRestClient().getForObject(buildUrl("getplayerstatus", user, "UTF-8"), PlayerStatus[].class)[0];
    }
}
