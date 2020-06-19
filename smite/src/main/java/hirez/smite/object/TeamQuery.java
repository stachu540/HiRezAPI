package hirez.smite.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

@Data
public class TeamQuery implements ReturnedMessage {

    @JsonProperty("Founder")
    private final String founder;
    @JsonProperty("Name")
    private final String name;
    @JsonProperty("Players")
    private final long players;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    @JsonProperty("Tag")
    private final String tag;
    @JsonProperty("TeamId")
    private final long teamId;

}
