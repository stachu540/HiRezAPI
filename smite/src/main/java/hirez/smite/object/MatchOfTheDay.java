package hirez.smite.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.adapters.CSV;
import hirez.api.object.adapters.DateTimeFormat;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MatchOfTheDay implements ReturnedMessage {
    private final String description;
    @JsonProperty("gameMode")
    private final String gameMode;
    @JsonProperty("maxPlayers")
    private final String maxPlayers;
    private final String name;
    @DateTimeFormat
    @JsonProperty("startDateTime")
    private final Date startDateTime;
    @CSV
    @JsonProperty("team1GodsCSV")
    private final List<Integer> team1Gods;
    @CSV
    @JsonProperty("team2GodsCSV")
    private final List<Integer> team2Gods;
    private final String title;
    @JsonProperty("ret_msg")
    private final String returnedMessage;

}
