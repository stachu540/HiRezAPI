package hirez.realm.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

@Data
public class PlayerQuery implements ReturnedMessage {
    private final long id;
    private final String name;
    private final int portalId;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    private final long steamId;
}
