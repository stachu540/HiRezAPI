package hirez.api.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.adapters.DateTimeFormat;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

import java.util.Date;

@Data
public class CreateSession implements ReturnedMessage {
    @JsonProperty("ret_msg")
    private String returnedMessage;
    private String sessionId;
    @DateTimeFormat
    private Date timestamp;

}
