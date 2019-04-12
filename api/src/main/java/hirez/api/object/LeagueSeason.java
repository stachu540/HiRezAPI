
package hirez.api.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

@Data
public class LeagueSeason implements ReturnedMessage {
    private final boolean complete;
    private final int id;
    private final String leagueDescription;
    private final String name;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    private final int round;
    private final int season;

}
