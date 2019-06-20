package hirez.smite.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.adapters.DateTimeFormat;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

import java.util.Date;

@Data
@SuppressWarnings("unused")
public class TopMatch implements ReturnedMessage {
    @JsonProperty("Ban1")
    private final String ban1;
    @JsonProperty("Ban1Id")
    private final long ban1Id;
    @JsonProperty("Ban2")
    private final String ban2;
    @JsonProperty("Ban2Id")
    private final long ban2Id;
    @DateTimeFormat
    @JsonProperty("Entry_Datetime")
    private final Date entryDatetime;
    @JsonProperty("LiveSpectators")
    private final int liveSpectators;
    @JsonProperty("Match")
    private final long matchId;
    @JsonProperty("Match_Time")
    private final long matchTime;
    @JsonProperty("OfflineSpectators")
    private final long offlineSpectators;
    @JsonProperty("Queue")
    private final String queue;
    @DateTimeFormat
    @JsonProperty("RecordingFinished")
    private final Date recordingFinished;
    @DateTimeFormat
    @JsonProperty("RecordingStarted")
    private final Date recordingStarted;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    @JsonProperty("Team1_AvgLevel")
    private final long team1AvgLevel;
    @JsonProperty("Team1_Gold")
    private final long team1Gold;
    @JsonProperty("Team1_Kills")
    private final long team1Kills;
    @JsonProperty("Team1_Score")
    private final long team1Score;
    @JsonProperty("Team2_AvgLevel")
    private final long team2AvgLevel;
    @JsonProperty("Team2_Gold")
    private final long team2Gold;
    @JsonProperty("Team2_Kills")
    private final long team2Kills;
    @JsonProperty("Team2_Score")
    private final long team2Score;
    @JsonProperty("WinningTeam")
    private final long winningTeam;

}
