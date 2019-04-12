
package hirez.realm.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

@Data
public class Player implements ReturnedMessage {
    private final String createdDatetime;
    private final long id;
    private final String lastLoginDatetime;
    private final long level;
    private final String name;
    @JsonProperty("portal")
    private final String portalName;
    private final int portalId;
    private final long portalUserid;
    private final String region;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    private final long steamId;

}
