package hirez.api.object;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.adapters.DurationTime;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Data
public class DataUsage implements ReturnedMessage {
    @JsonAlias("Active_Sessions")
    private final int activeSessions;
    @JsonAlias("Concurrent_Sessions")
    private final int concurrentSessions;
    @JsonAlias("Request_Limit_Daily")
    private final long requestLimitDaily;
    @JsonAlias("Session_Cap")
    private final int sessionCap;
    @DurationTime(ChronoUnit.MINUTES)
    @JsonAlias("Session_Time_Limit")
    private final Duration sessionTimeLimit;
    @JsonAlias("Total_Requests_Today")
    private final long totalRequestsToday;
    @JsonAlias("Total_Sessions_Today")
    private final long totalSessionsToday;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
}
