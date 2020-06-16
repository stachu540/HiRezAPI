package hirez.api;

import hirez.api.object.statuspage.*;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StatusPage {
    private final Endpoint endpoint;

    public Single<Summary> getSummary() {
        return endpoint.get(Summary.class, combineUrl("/summary.json"));
    }

    public Single<StatusData> getStatus() {
        return endpoint.get(StatusData.class, combineUrl("/status.json"));
    }

    public Single<Components> getComponents() {
        return endpoint.get(Components.class, combineUrl("/components.json"));
    }

    public Single<Incidents> getUnresolvedIncidents() {
        return endpoint.get(Incidents.class, combineUrl("/incidents/unresolved.json"));
    }

    public Single<Incidents> getIncidents() {
        return endpoint.get(Incidents.class, combineUrl("/incidents.json"));
    }

    public Single<ScheduledMaintenances> getUpcomingScheduledMaintenances() {
        return endpoint.get(ScheduledMaintenances.class, combineUrl("/scheduled-maintenances/upcoming.json"));
    }

    public Single<ScheduledMaintenances> getActiveScheduledMaintenances() {
        return endpoint.get(ScheduledMaintenances.class, combineUrl("/scheduled-maintenances/active.json"));
    }

    public Single<ScheduledMaintenances> getAllScheduledMaintenances() {
        return endpoint.get(ScheduledMaintenances.class, combineUrl("/scheduled-maintenances.json"));
    }

    private String combineUrl(String endpoint) {
        return "http://stk4xr7r1y0r.statuspage.io/api/v2" + endpoint;
    }
}
