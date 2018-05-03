package hirezapi.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class SimplePlayer extends Model {
    @JsonProperty("AccountLevel")
    private int accountLevel;
    @JsonProperty("JoinedDatetime")
    private Instant createdAt;
    @JsonProperty("LastLoginDatetime")
    private Instant lastLogin;
    @JsonProperty("Name")
    private String name;
}
