package hirez.api.object.statuspage;

import hirez.api.object.interfaces.IDObject;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class IncidentUpdate implements IDObject<String> {
    private final String status;
    private final String body;
    private final Date createdAt;
    private final Date updatedAt;
    private final Date displayAt;
    private final List<AffectedComponent> affectedComponents;
    private final boolean deliverNotifications;
    private final long tweetId;
    private final String id;
    private final String incidentId;
    private final String customTweet;
}
