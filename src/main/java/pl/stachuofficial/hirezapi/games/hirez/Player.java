package pl.stachuofficial.hirezapi.games.hirez;

import org.json.JSONObject;
import pl.stachuofficial.hirezapi.api.StringData;

import java.util.Map;

public class Player {
    private final Map<String, Object> playerdata;

    public Player(StringData player, StringData achievements) {
        JSONObject stats = achievements.toJsonObject();
        JSONObject user = player.toJsonObject();
        stats.remove("Id");
        String tag = user.getString("Name").replace(stats.getString("Name"), "");
        user.put("Name", stats.getString("Name"));
        user.put("Team_Tag", tag.substring(1, tag.length() - 1));
        stats.remove("Name");
        user.put("Stats", stats);

        this.playerdata = user.toMap();
    }
}
