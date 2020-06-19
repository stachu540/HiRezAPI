package hirez.paladins.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.adapters.DateTimeFormat;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

import java.util.Date;

@Data
public class LiveMatch implements ReturnedMessage {
    @JsonProperty("Account_Champions_Played")
    private final int accountChampionsPlayed;
    @JsonProperty("Account_Level")
    private final int accountLevel;
    @JsonProperty("ChampionId")
    private final long championId;
    @JsonProperty("ChampionLevel")
    private final int championLevel;
    @JsonProperty("ChampionName")
    private final String championName;
    @JsonProperty("Mastery_Level")
    private final int masteryLevel;
    @JsonProperty("Match")
    private final long matchId;
    @JsonProperty("Queue")
    private final int queue;
    @JsonProperty("Skin")
    private final String skinName;
    @JsonProperty("SkinId")
    private final long skinId;
    @JsonProperty("Tier")
    private final int tier;
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
    @JsonProperty("taskForce")
    private final int taskForce;
    @JsonProperty("tierLosses")
    private final int tierLosses;
    @JsonProperty("tierWins")
    private final int tierWins;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
}
