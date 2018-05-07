package hirezapi.json.status;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public static Status get(String status) {
      return valueOf(status.replace(" ", "_"));
    }

    @Override
    public String toString() {
      return this.name().replace("_", " ");
    }
  }
}
