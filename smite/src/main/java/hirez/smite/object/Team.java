package hirez.smite.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Team {

    @JsonProperty("Founder")
    private final String founder;
    @JsonProperty("FounderId")
    private final String founderId;
    @JsonProperty("Losses")
    private final long losses;
    @JsonProperty("Name")
    private final String name;
    @JsonProperty("Players")
    private final long players;
    @JsonProperty("Rating")
    private final long rating;
    @JsonProperty("ret_msg")
    private final Object retMsg;
    @JsonProperty("Tag")
    private final String tag;
    @JsonProperty("TeamId")
    private final long teamId;
    @JsonProperty("Wins")
    private final long wins;

}
