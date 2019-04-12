
package hirez.api.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

@Data
public class DataUsage implements ReturnedMessage {
    private final int activeSessions;
    private final int concurrentSessions;
    private final long requestLimitDaily;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    private final int sessionCap;
    private final long sessionTimeLimit;
    private final long totalRequestsToday;
    private final long totalSessionsToday;
}
