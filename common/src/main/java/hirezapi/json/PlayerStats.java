package hirezapi.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PlayerStats extends Model {
  @JsonProperty("Assists")
  private Integer assists;
  @JsonProperty("Deaths")
  private Integer deaths;
  @JsonProperty("Gold")
  private Long gold;
  @JsonProperty("Kills")
  private Integer kills;
  @JsonProperty("LastPlayed")
  private Instant lastPlayed;
  @JsonProperty("Losses")
  private Integer losses;
  @JsonProperty("Matches")
  private Integer matches;
  @JsonProperty("Minutes")
  private Integer minutes;
  @JsonProperty("Queue")
  private String queue;
  @JsonProperty("Wins")
  private Integer wins;
  private Long playerId;
}
