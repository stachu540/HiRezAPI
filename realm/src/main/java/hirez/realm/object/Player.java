package hirez.realm.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.adapters.DateTimeFormat;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

import java.util.Date;

@Data
public class Player implements ReturnedMessage {
    @DateTimeFormat
    private final Date createdDatetime;
    private final long id;
    @DateTimeFormat
    private final Date lastLoginDatetime;
    private final int level;
    private final String name;
    private final String platform;
    @JsonProperty("portal")
    private final String portalName;
    private final int portalId;
    private final long portalUserid;
    private final String region;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    private final long steamId;

}
