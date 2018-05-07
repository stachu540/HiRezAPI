package hirezapi.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.time.Instant;
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
public class ProLeagueMatch extends Model {
  private long homeClanId;
  private String homeClanName;
  private String homeClanTag;

  private long awayClanId;
  private String awayClanName;
  private String awayClanTag;

  private Instant matchDate;
  private int matchNumber;
  private String matchStatus;
  private String region;
  private String tournamentName;

  private long winnerClanId;
}
