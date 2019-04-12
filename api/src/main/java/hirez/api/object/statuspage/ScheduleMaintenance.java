package hirez.api.object.statuspage;

import hirez.api.object.interfaces.IDObject;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ScheduleMaintenance implements IDObject<String> {
    private final String name;
    private final String status;
    private final Date createdAt;
    private final Date updatedAt;
    private final Date monitoringAt;
    private final Date resolvedAt;
    private final String shortlink;
    private final Date scheduledFor;
    private final Date scheduledUntil;
    private final Date startedAt;
    private final String id;
    private final String pageId;
    private final List<IncidentUpdate> incidentUpdates;
    private final List<Component> components;
    private final String impact;
}
