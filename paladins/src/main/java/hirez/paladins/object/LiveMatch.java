
package hirez.paladins.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.adapters.DateTimeFormat;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

import java.util.Date;

@Data
public class LiveMatch implements ReturnedMessage {
    private final int accountLevel;
    private final long championId;
    private final String championName;
    private final String mapGame;
    private final int masteryLevel;
    @JsonProperty("Match")
    private final long matchId;
    @DateTimeFormat
    private final Date playerCreated;
    private final long playerId;
    private final String playerName;
    @JsonProperty("Queue")
    private final int queue;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    private final long skinId;
    private final int taskForce;
    private final int tier;
    private final int tierLosses;
    private final int tierWins;
}
