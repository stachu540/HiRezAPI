package hirez.smite.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QueueStat {

    @JsonProperty("Assists")
    private final long assists;
    @JsonProperty("Deaths")
    private final long deaths;
    @JsonProperty("God")
    private final String god;
    @JsonProperty("GodId")
    private final long godId;
    @JsonProperty("Gold")
    private final long gold;
    @JsonProperty("Kills")
    private final long kills;
    @JsonProperty("LastPlayed")
    private final String lastPlayed;
    @JsonProperty("Losses")
    private final long losses;
    @JsonProperty("Matches")
    private final long matches;
    @JsonProperty("Minutes")
    private final long minutes;
    @JsonProperty("player_id")
    private final String playerId;
    @JsonProperty("Queue")
    private final String queue;
    @JsonProperty("ret_msg")
    private final Object retMsg;
    @JsonProperty("Wins")
    private final long wins;

}
