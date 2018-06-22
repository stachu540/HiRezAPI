package hirezapi.endpoints;

import hirezapi.HiRezApi;
import hirezapi.json.Achievements;
import hirezapi.json.Player;
import hirezapi.json.Friend;
import hirezapi.json.PlayerStatus;

import java.util.Arrays;
import java.util.List;

public class UserEndpoint extends AbstractEndpoint {
  public UserEndpoint(HiRezApi api) {
    super(api);
  }

  public Player getPlayer(String user) {
    return api.getRestController()
          .request(buildUrl("getplayer", user), Player[].class)[0];
  }

  public Achievements getPlayerAchievments(long id) {
    return api.getRestController()
          .request(buildUrl("getplayerachievements", Long.toString(id)), Achievements.class);
  }

  public PlayerStatus getPlayerStatus(String user) {
    return api.getRestController()
          .request(buildUrl("getplayerstatus", user), PlayerStatus[].class)[0];
  }

  public List<Friend> getFriends(String user) {
    if (!api.getConfiguration().getPlatform().getPlatform().equalsIgnoreCase("PC")) {
      throw new UnsupportedOperationException("Friend list is supported only PC platform.");
    }
    return Arrays.asList(api.getRestController()
          .request(buildUrl("getfriends", user), Friend[].class));
  }
}
