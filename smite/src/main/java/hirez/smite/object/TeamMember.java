package hirez.smite.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.adapters.DateTimeFormat;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

import java.util.Date;

@Data
public class TeamMember implements ReturnedMessage {

    @JsonProperty("AccountLevel")
    private final long accountLevel;
    @DateTimeFormat
    @JsonProperty("JoinedDatetime")
    private final Date joinedDatetime;
    @DateTimeFormat
    @JsonProperty("LastLoginDatetime")
    private final Date lastLoginDatetime;
    @JsonProperty("Name")
    private final String name;
    @JsonProperty("ret_msg")
    private final String returnedMessage;

}
