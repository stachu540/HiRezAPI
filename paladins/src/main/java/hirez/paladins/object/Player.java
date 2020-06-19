package hirez.paladins.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.MergedAccount;
import hirez.api.object.adapters.DateTimeFormat;
import hirez.api.object.adapters.DurationTime;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Data
public class Player implements ReturnedMessage {
    @JsonProperty("ActivePlayerId")
    private final long activePlayerId;
    @JsonProperty("AvatarId")
    private final long avatarId;
    @JsonProperty("AvatarURL")
    private final String avatarURL;
    @DateTimeFormat
    @JsonProperty("Created_Datetime")
    private final Date createdDatetime;
    @DurationTime(ChronoUnit.HOURS)
    @JsonProperty("HoursPlayed")
    private final Duration hoursPlayed;
    @JsonProperty("Id")
    private final long id;
    @DateTimeFormat
    @JsonProperty("Last_Login_Datetime")
    private final Date lastLoginDatetime;
    @JsonProperty("Leaves")
    private final int leaves;
    @JsonProperty("Level")
    private final int level;
    @JsonProperty("LoadingFrame")
    private final String loadingFrame;
    @JsonProperty("Losses")
    private final int losses;
    @JsonProperty("MasteryLevel")
    private final int masteryLevel;
    @JsonProperty("MergedPlayers")
    private final MergedAccount[] mergedPlayers;
    @DurationTime(ChronoUnit.MINUTES)
    @JsonProperty("MinutesPlayed")
    private final Duration minutesPlayed;
    @JsonProperty("Name")
    private final String name;
    @JsonProperty("Personal_Status_Message")
    private final String personalStatusMessage;
    @JsonProperty("Platform")
    private final String platform;
    @JsonProperty("RankedConquest")
    private final Rank rankedConquest;
    @JsonProperty("RankedController")
    private final Rank rankedController;
    @JsonProperty("RankedKBM")
    private final Rank rankedKBM;
    @JsonProperty("Region")
    private final String region;
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
    @JsonProperty("Title")
    private final String title;
    @JsonProperty("Total_Achievements")
    private final int totalAchievements;
    @JsonProperty("Total_Worshippers")
    private final int totalWorshippers;
    @JsonProperty("Total_XP")
    private final int totalExp;
    @JsonProperty("Wins")
    private final int wins;
    @JsonProperty("hz_gamer_tag")
    private final String hiRezGamerTag;
    @JsonProperty("hz_player_name")
    private final String hiRezPlayerName;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
}
