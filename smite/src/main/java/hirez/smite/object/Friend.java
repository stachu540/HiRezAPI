package hirez.smite.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

@Data
public class Friend implements ReturnedMessage {
    private final long accountId;
    private final String avatarUrl;
    private final String friendFlags;
    private final String name;
    private final long playerId;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    private final String status;
}
