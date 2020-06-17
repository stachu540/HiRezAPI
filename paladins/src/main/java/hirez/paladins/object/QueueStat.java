package hirez.paladins.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.adapters.DateTimeFormat;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

import java.util.Date;

@Data
public class QueueStat implements ReturnedMessage {
    @JsonProperty("Assists")
    private final int assists;
    @JsonProperty("Champion")
    private final String champion;
    @JsonProperty("ChampionId")
    private final long championId;
    @JsonProperty("Deaths")
    private final int deaths;
    @JsonProperty("Gold")
    private final int gold;
    @JsonProperty("Kills")
    private final int kills;
    @DateTimeFormat
    @JsonProperty("LastPlayed")
    private final Date lastPlayed;
    @JsonProperty("Losses")
    private final int losses;
    @JsonProperty("Matches")
    private final int matches;
    @JsonProperty("Minutes")
    private final long minutes;
    @JsonProperty("player_id")
    private final long playerId;
    @JsonProperty("Queue")
    private final String queue;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    @JsonProperty("Wins")
    private final int wins;

}
