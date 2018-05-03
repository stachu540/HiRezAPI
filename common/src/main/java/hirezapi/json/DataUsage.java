package hirezapi.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DataUsage extends Model {
    @JsonProperty("Active_Sessions")
    private int activeSessions;
    @JsonProperty("Concurrent_Sessions")
    private int concurrentSessions;
    @JsonProperty("Request_Limit_Daily")
    private int requestLimitDaily;
    @JsonProperty("Session_Cap")
    private int sessionCap;
    @JsonProperty("Session_Time_Limit")
    private int sessionTimeLimit;
    @JsonProperty("Total_Requests_Today")
    private int totalRequestsToday;
    @JsonProperty("Total_Sessions_Today")
    private int totalSessionsToday;
}
