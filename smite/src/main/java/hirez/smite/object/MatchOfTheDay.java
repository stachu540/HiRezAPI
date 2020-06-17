package hirez.smite.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MatchOfTheDay {

    private final String description;
    private final String gameMode;
    private final String maxPlayers;
    private final String name;
    @JsonProperty("ret_msg")
    private final Object retMsg;
    private final String startDateTime;
    private final String team1GodsCSV;
    private final String team2GodsCSV;
    private final String title;

}
