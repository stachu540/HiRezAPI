package hirezapi.json.status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncidentInfo {
    private Instant timestamp;
    private Status status;
    private String description;

    public enum Status {
        Investigating,
        Identified,
        Monitoring,
        Verifying,
        Resolved,
        Scheduled,
        Update,
        In_progress,
        Completed;

        @Override
        public String toString() {
            return this.name().replace("_", " ");
        }

        public static Status get(String status) {
            return valueOf(status.replace(" ", "_"));
        }
    }
}
