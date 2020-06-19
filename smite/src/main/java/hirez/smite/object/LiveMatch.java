package hirez.smite.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.adapters.DateTimeFormat;
import lombok.Data;

import java.util.Date;

@Data
public class LiveMatch {
    @JsonProperty("Account_Gods_Played")
    private final long accountGodsPlayed;
    @JsonProperty("Account_Level")
    private final long accountLevel;
    @JsonProperty("GodId")
    private final long godId;
    @JsonProperty("GodLevel")
    private final int godLevel;
    @JsonProperty("GodName")
    private final String godName;
    @JsonProperty("Mastery_Level")
    private final long masteryLevel;
    @JsonProperty("Match")
    private final long matchId;
    @JsonProperty("Queue")
    private final int queueId;
    @JsonProperty("Rank_Stat")
    private final double rankStat;
    @JsonProperty("SkinId")
    private final long skinId;
    @JsonProperty("Tier")
    private final long tier;
    @JsonProperty("mapGame")
    private final String mapGame;
    @DateTimeFormat
    @JsonProperty("playerCreated")
    private final Date playerCreated;
    @JsonProperty("playerId")
    private final long playerId;
    @JsonProperty("playerName")
    private final String playerName;
    @JsonProperty("playerRegion")
    private final String playerRegion;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    @JsonProperty("taskForce")
    private final long taskForce;
    @JsonProperty("tierLosses")
    private final long tierLosses;
    @JsonProperty("tierWins")
    private final long tierWins;
}
