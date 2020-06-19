package hirez.api.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.adapters.DateTimeFormat;
import lombok.Data;

import java.util.Date;

@Data
public class MergedAccount {
    @JsonProperty("merge_datetime")
    @DateTimeFormat("MMM dd yyyy  h:mma")
    private final Date mergeDatetime;
    @JsonProperty("playerId")
    private final long playerId;
    @JsonProperty("portalId")
    private final int portalId;
}
