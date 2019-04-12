
package hirez.paladins.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

@Data
public class ChampionLeaderboard implements ReturnedMessage {
    private final long championId;
    private final int losses;
    private final long playerId;
    private final String playerName;
    private final double playerRanking;
    private final int rank;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    private final int wins;
}
