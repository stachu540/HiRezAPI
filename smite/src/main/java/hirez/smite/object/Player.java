package hirez.smite.object;

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
    @JsonProperty("Avatar_URL")
    private final String avatarURL;
    @DateTimeFormat("M/d/yyyy h:mm:ss a")
    @JsonProperty("Created_Datetime")
    private final Date createdDatetime;
    @DurationTime(ChronoUnit.HOURS)
    @JsonProperty("HoursPlayed")
    private final Duration hoursPlayed;
    @JsonProperty("Id")
    private final long id;
    @DateTimeFormat("M/d/yyyy h:mm:ss a")
    @JsonProperty("Last_Login_Datetime")
    private final Date lastLoginDatetime;
    @JsonProperty("Leaves")
    private final long leaves;
    @JsonProperty("Level")
    private final long level;
    @JsonProperty("Losses")
    private final long losses;
    @JsonProperty("MasteryLevel")
    private final long masteryLevel;
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
    @JsonProperty("Rank_Stat_Conquest")
    private final double rankStatConquest;
    @JsonProperty("Rank_Stat_Conquest_Controller")
    private final double rankStatConquestController;
    @JsonProperty("Rank_Stat_Duel")
    private final long rankStatDuel;
    @JsonProperty("Rank_Stat_Duel_Controller")
    private final long rankStatDuelController;
    @JsonProperty("Rank_Stat_Joust")
    private final long rankStatJoust;
    @JsonProperty("Rank_Stat_Joust_Controller")
    private final long rankStatJoustController;
    @JsonProperty("RankedConquest")
    private final Rank rankedConquest;
    @JsonProperty("RankedConquestController")
    private final Rank rankedConquestController;
    @JsonProperty("RankedDuel")
    private final Rank rankedDuel;
    @JsonProperty("RankedDuelController")
    private final Rank rankedDuelController;
    @JsonProperty("RankedJoust")
    private final Rank rankedJoust;
    @JsonProperty("RankedJoustController")
    private final Rank rankedJoustController;
    @JsonProperty("Region")
    private final String region;
    @JsonProperty("TeamId")
    private final long teamId;
    @JsonProperty("Team_Name")
    private final String teamName;
    @JsonProperty("Tier_Conquest")
    private final long tierConquest;
    @JsonProperty("Tier_Duel")
    private final long tierDuel;
    @JsonProperty("Tier_Joust")
    private final long tierJoust;
    @JsonProperty("Total_Achievements")
    private final long totalAchievements;
    @JsonProperty("Total_Worshippers")
    private final long totalWorshippers;
    @JsonProperty("Wins")
    private final long wins;
    @JsonProperty("hz_gamer_tag")
    private final String hzGamerTag;
    @JsonProperty("hz_player_name")
    private final String hzPlayerName;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
}
