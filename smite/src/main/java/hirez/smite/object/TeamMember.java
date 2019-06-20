package hirez.smite.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class TeamMember {

    @JsonProperty("AccountLevel")
    private final long accountLevel;
    @JsonProperty("JoinedDatetime")
    private final String joinedDatetime;
    @JsonProperty("LastLoginDatetime")
    private final String lastLoginDatetime;
    @JsonProperty("Name")
    private final String name;
    @JsonProperty("ret_msg")
    private final Object retMsg;

}
