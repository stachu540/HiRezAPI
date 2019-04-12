package hirez.api;

import hirez.api.object.statuspage.*;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StatusPage {
    private final RestClient client;

    public Single<Summary> getSummary() {
        return client.get(Summary.class, combineUrl("/summary.json"));
    }

    public Single<StatusData> getStatus() {
        return client.get(StatusData.class, combineUrl("/status.json"));
    }

    public Single<Components> getComponents() {
        return client.get(Components.class, combineUrl("/components.json"));
    }

    public Single<Incidents> getUnresolvedIncidents() {
        return client.get(Incidents.class, combineUrl("/incidents/unresolved.json"));
    }

    public Single<Incidents> getIncidents() {
        return client.get(Incidents.class, combineUrl("/incidents.json"));
    }

    public Single<ScheduledMaintenances> getUpcomingScheduledMaintenances() {
        return client.get(ScheduledMaintenances.class, combineUrl("/scheduled-maintenances/upcoming.json"));
    }

    public Single<ScheduledMaintenances> getActiveScheduledMaintenances() {
        return client.get(ScheduledMaintenances.class, combineUrl("/scheduled-maintenances/active.json"));
    }

    public Single<ScheduledMaintenances> getAllScheduledMaintenances() {
        return client.get(ScheduledMaintenances.class, combineUrl("/scheduled-maintenances.json"));
    }

    private String combineUrl(String endpoint) {
        return "http://stk4xr7r1y0r.statuspage.io/api/v2" + endpoint;
    }
}
