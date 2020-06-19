package hirez.api.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.adapters.DateTimeFormat;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

import javax.annotation.Nullable;
import java.util.Date;

@Data
public class HiRezServer implements ReturnedMessage {
    @Nullable
    @DateTimeFormat("yyyy-MM-dd  H:mm:ss.SSS")
    private final Date entryDatetime;
    private final String environment;
    private final boolean limitedAccess;
    private final String platform;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    private final Status status;
    private final String version;

    public enum Status {
        UP,
        DOWN
    }
}
