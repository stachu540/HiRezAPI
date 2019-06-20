package hirez.paladins.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.MergedAccount;
import hirez.api.object.adapters.DateTimeFormat;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

import java.util.Date;

@Data
public class Player implements ReturnedMessage {
    @JsonProperty("ActivePlayerId")
    private final long activePlayerId;
    @DateTimeFormat
    @JsonProperty("Created_Datetime")
    private final Date createdDatetime;
    @JsonProperty("HoursPlayed")
    private final int hoursPlayed;
    @JsonProperty("hz_gamer_tag")
    private final String hiRezGamerTag;
    @JsonProperty("hz_player_name")
    private final String hiRezPlayerName;
    @JsonProperty("Id")
    private final long id;
    @DateTimeFormat
    @JsonProperty("Last_Login_Datetime")
    private final Date lastLoginDatetime;
    @JsonProperty("Leaves")
    private final int leaves;
    @JsonProperty("Level")
    private final int level;
    @JsonProperty("Losses")
    private final int losses;
    @JsonProperty("MasteryLevel")
    private final int masteryLevel;
    @JsonProperty("MergedPlayers")
    private final MergedAccount[] mergedPlayers;
    @JsonProperty("Name")
    private final String name;
    @JsonProperty("Personal_Status_Message")
    private final String personalStatusMessage;
    @JsonProperty("Platform")
    private final String platform;
    @JsonProperty("RankedConquest")
    private final RankItem rankedConquest;
    @JsonProperty("RankedController")
    private final RankItem rankedController;
    @JsonProperty("RankedKBM")
    private final RankItem rankedKBM;
    @JsonProperty("Region")
    private final String region;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    @JsonProperty("TeamId")
    private final long teamId;
    @JsonProperty("Team_Name")
    private final String teamName;
    @JsonProperty("Tier_Conquest")
    private final int tierConquest;
    @JsonProperty("Tier_RankedController")
    private final int tierRankedController;
    @JsonProperty("Tier_RankedKBM")
    private final int tierRankedKBM;
    @JsonProperty("Total_Achievements")
    private final int totalAchievements;
    @JsonProperty("Total_Worshippers")
    private final int totalWorshippers;
    @JsonProperty("Wins")
    private final int wins;

    @Data
    public static class RankItem implements ReturnedMessage {
        @JsonProperty("Leaves")
        private final int leaves;
        @JsonProperty("Losses")
        private final int losses;
        @JsonProperty("Name")
        private final String name;
        @JsonProperty("player_id")
        private final long playerId;
        @JsonProperty("Points")
        private final int points;
        @JsonProperty("PrevRank")
        private final int prevRank;
        @JsonProperty("Rank")
        private final int rank;
        @JsonProperty("ret_msg")
        private final String returnedMessage;
        @JsonProperty("Season")
        private final int season;
        @JsonProperty("Tier")
        private final int tier;
        @JsonProperty("Trend")
        private final int trend;
        @JsonProperty("Wins")
        private final int wins;

    }
}
