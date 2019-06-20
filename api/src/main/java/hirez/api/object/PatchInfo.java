package hirez.api.object;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

@Data
public class PatchInfo implements ReturnedMessage {
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    @JsonAlias("version_string")
    private final String version;
}
