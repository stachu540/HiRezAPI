package hirez.smite.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class TeamQuery {

    @JsonProperty("Founder")
    private final String founder;
    @JsonProperty("Name")
    private final String name;
    @JsonProperty("Players")
    private final long players;
    @JsonProperty("ret_msg")
    private final Object retMsg;
    @JsonProperty("Tag")
    private final String tag;
    @JsonProperty("TeamId")
    private final long teamId;

}
