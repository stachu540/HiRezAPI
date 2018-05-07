package hirezapi.json.status;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Incident {
  private String title;
  private String url;
  private List<IncidentInfo> incidents;

  public IncidentInfo getLastIncident() {
    return incidents.get(0);
  }
}
