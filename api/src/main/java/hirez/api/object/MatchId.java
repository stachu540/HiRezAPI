package hirez.api.object;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.adapters.TextToBoolean;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

@Data
public class MatchId implements ReturnedMessage {
    @TextToBoolean
    @JsonAlias({"Active_Flag", "active_flag"})
    private final boolean active;
    @JsonAlias({"match", "Match"})
    private final long id;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
}
