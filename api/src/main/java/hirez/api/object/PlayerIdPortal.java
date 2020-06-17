package hirez.api.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PlayerIdPortal {
    private final long playerId;
    private final String portal;
    private final int portalId;
    @JsonProperty("ret_msg")
    private final String returnedMessage;

}
