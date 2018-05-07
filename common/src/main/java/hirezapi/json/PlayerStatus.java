package hirezapi.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PlayerStatus extends Model {
  @JsonProperty("status")
  private Status status;

  @JsonProperty("personal_status_message")
  private String statusMessage;

  @JsonProperty("Match")
  private long matchId;

  @JsonProperty("status_string")
  private String stringifyStatus;

  public enum Status {
    OFFLINE,
    IN_LOBBY,
    GOD_SELECTION,
    IN_GAME,
    ONLINE,
    UNKNOWN
  }
}
