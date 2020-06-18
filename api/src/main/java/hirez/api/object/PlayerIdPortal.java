package hirez.api.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.adapters.TextToBoolean;
import lombok.Data;

@Data
public class PlayerIdPortal {
    @JsonProperty("Name")
    private final String name;
    private final long playerId;
    private final String portal;
    private final int portalId;
    @TextToBoolean
    private final boolean privacyFlag;
    @JsonProperty("ret_msg")
    private final String returnedMessage;

}
