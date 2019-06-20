package hirez.smite.object;

import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Friend implements ReturnedMessage {
    private final long accountId;
    private final String avatarUrl;
    private final String name;
    private final long playerId;
    private final String returnedMessage;

}
