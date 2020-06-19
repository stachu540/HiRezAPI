package hirez.smite.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

@Data
public class Rank implements ReturnedMessage {
    @JsonProperty("Leaves")
    private final int leaves;
    @JsonProperty("Losses")
    private final int losses;
    @JsonProperty("Name")
    private final String name;
    @JsonProperty("Points")
    private final int points;
    @JsonProperty("PrevRank")
    private final int prevRank;
    @JsonProperty("Rank")
    private final int rank;
    @JsonProperty("Rank_Stat")
    private final double rankStat;
    @JsonProperty("Rank_Variance")
    private final int rankVariance;
    @JsonProperty("Season")
    private final int season;
    @JsonProperty("Tier")
    private final int tier;
    @JsonProperty("Trend")
    private final int trend;
    @JsonProperty("Wins")
    private final int wins;
    private final long playerId;
    @JsonProperty("ret_msg")
    private final String returnedMessage;

}
