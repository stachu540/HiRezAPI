package hirez.smite.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.adapters.DateTimeFormat;
import lombok.Data;

import java.util.Date;

@Data
public class LiveMatch {
    private final long accountLevel;
    private final long godId;
    private final String godName;
    private final String mapGame;
    private final long masteryLevel;
    @JsonProperty("Match")
    private final long matchId;
    @DateTimeFormat
    private final Date playerCreated;
    private final long playerId;
    private final String playerName;
    @JsonProperty("Queue")
    private final int queueId;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    private final long skinId;
    private final long taskForce;
    private final long tier;
    private final long tierLosses;
    private final long tierWins;
}
