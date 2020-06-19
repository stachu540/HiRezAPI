package hirez.api.object;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.adapters.TextToBoolean;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
public class PlayerQuery implements ReturnedMessage {
    @JsonAlias("Name")
    private final String name;
    @JsonProperty("hz_player_name")
    private final String hiRezPlayerName;
    @JsonAlias("player_id")
    private final long id;
    @JsonProperty("portal_id")
    private final int portalId;
    @TextToBoolean
    @Accessors(fluent = true)
    @JsonProperty("privacy_flag")
    private final boolean isPrivate;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
}
