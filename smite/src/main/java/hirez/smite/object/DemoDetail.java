package hirez.smite.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.adapters.DateTimeFormat;
import hirez.api.object.adapters.DurationTime;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

import javax.annotation.Nullable;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Data
public class DemoDetail implements ReturnedMessage {
    @JsonProperty("BanId1")
    private final long banId1;
    @JsonProperty("BanId2")
    private final long banId2;
    @JsonProperty("BanId3")
    private final long banId3;
    @JsonProperty("BanId4")
    private final long banId4;
    @DateTimeFormat
    @JsonProperty("Entry_Datetime")
    private final Date entryDatetime;
    @JsonProperty("Match")
    private final long id;
    @DurationTime
    @JsonProperty("Match_Time")
    private final Duration matchTime;
    @JsonProperty("Offline_Spectators")
    private final int offlineSpectators;
    @JsonProperty("Queue")
    private final int queueId;
    @JsonProperty("Realtime_Spectators")
    private final int realtimeSpectators;
    @Nullable
    @DateTimeFormat
    @JsonProperty("Recording_Ended")
    private final Date recordingEnded;
    @Nullable
    @DateTimeFormat
    @JsonProperty("Recording_Started")
    private final Date recordingStarted;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    @JsonProperty("Team1_AvgLevel")
    private final int team1AvgLevel;
    @JsonProperty("Team1_Gold")
    private final int team1Gold;
    @JsonProperty("Team1_Kills")
    private final int team1Kills;
    @JsonProperty("Team1_Score")
    private final int team1Score;
    @JsonProperty("Team2_AvgLevel")
    private final int team2AvgLevel;
    @JsonProperty("Team2_Gold")
    private final int team2Gold;
    @JsonProperty("Team2_Kills")
    private final int team2Kills;
    @JsonProperty("Team2_Score")
    private final int team2Score;
    @JsonProperty("Winning_Team")
    private final int winningTeam;

}
