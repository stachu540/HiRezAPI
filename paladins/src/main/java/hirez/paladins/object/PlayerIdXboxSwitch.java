
package hirez.paladins.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class PlayerIdXboxSwitch implements ReturnedMessage {
    private final String gamerTag;
    @JsonProperty("Name")
    private final String name;
    private final String platform;
    private final long playerId;
    private final long portalUserid;
    @JsonProperty("ret_msg")
    private final String returnedMessage;

}
