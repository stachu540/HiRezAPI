
package hirez.realm.object;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.adapters.DateTimeFormat;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

@Data
public class Leaderboard implements ReturnedMessage {
    @DateTimeFormat("yyyy-MM-dd'T'hh:mm:ss.SSS")
    private final Date lastUpdated;
    @JsonProperty("leaderboard_details")
    private final List<Detail> leaderboardDetails;
    private final String queue;
    private final int queueId;
    @JsonProperty("ret_msg")
    private final String returnedMessage;

    @Data
    public static class Detail {
        private final int matches;
        private final long playerId;
        private final String playerName;
        private final int rank;
        private final double teamAvgPlacement;
        private final int teamWins;
        private final double winPercentage;

    }
}
