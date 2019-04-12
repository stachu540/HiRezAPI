
package hirez.smite.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class GodRank implements ReturnedMessage {
    @JsonProperty("Assists")
    private final int assists;
    @JsonProperty("Deaths")
    private final int deaths;
    private final String god;
    @JsonProperty("god_id")
    private final long godId;
    @JsonProperty("Kills")
    private final int kills;
    @JsonProperty("Losses")
    private final int losses;
    @JsonProperty("MinionKills")
    private final int minionKills;
    @JsonProperty("player_id")
    private final long playerId;
    @JsonProperty("Rank")
    private final int rank;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    @JsonProperty("Wins")
    private final int wins;
    @JsonProperty("Worshippers")
    private final int worshippers;

}
