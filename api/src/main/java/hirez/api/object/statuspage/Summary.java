package hirez.api.object.statuspage;

import lombok.Data;

import java.util.List;

@Data
public class Summary {
    private final StatusData status;
    private final List<Component> components;
    private final List<Incident> incidents;
    private final List<ScheduleMaintenance> scheduledMaintenances;
    private final Page page;
}
