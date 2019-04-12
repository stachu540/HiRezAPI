package hirez.api.object;

import hirez.api.object.adapters.DateTimeFormat;
import lombok.Data;

import java.util.Date;

@Data
public class MergedAccount {
    @DateTimeFormat("M/dd/yyyy  h:mm:ssa")
    private final Date mergeDatetime;
    private final long playerId;
    private final int portalId;
}
