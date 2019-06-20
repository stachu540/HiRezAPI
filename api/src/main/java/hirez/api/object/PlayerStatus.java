package hirez.api.object;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

@Data
public class PlayerStatus implements ReturnedMessage {
    @JsonProperty("Match")
    private final long matchId;
    private final int matchQueueId;
    private final String personalStatusMessage;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    @JsonAlias("status")
    private final int statusId;
    @JsonAlias("status")
    private final String statusString;

}
