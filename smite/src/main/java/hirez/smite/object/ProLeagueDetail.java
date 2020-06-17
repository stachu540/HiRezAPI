package hirez.smite.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.adapters.DateTimeFormat;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

import java.util.Date;

@Data
public class ProLeagueDetail implements ReturnedMessage {
    private final long awayTeamClanId;
    private final String awayTeamName;
    private final String awayTeamTagname;
    private final long homeTeamClanId;
    private final String homeTeamName;
    private final String homeTeamTagname;
    private final int mapInstanceId;
    @DateTimeFormat
    private final Date matchDate;
    private final int matchNumber;
    private final String matchStatus;
    private final long matchupId;
    private final String region;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    private final String tournamentName;
    private final long winningTeamClanId;
}
