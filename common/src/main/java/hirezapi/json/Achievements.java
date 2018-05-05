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
public class Achievements extends Model {
    @JsonProperty("KillingSpree")
    private long killingSprees;
    @JsonProperty("RampageSpree")
    private long rampages;
    @JsonProperty("UnstoppableSpree")
    private long unstoppables;
    @JsonProperty("DivineSpree")
    private long divines;
    @JsonProperty("ImmortalSpree")
    private long immortals;
    @JsonProperty("GodLikeSpree")
    private long godlikes;
    @JsonProperty("ShutdownSpree")
    private long shutdowns;
    @JsonProperty("DoubleKills")
    private long doubleKills;
    @JsonProperty("TripleKills")
    private long tripleKills;
    @JsonProperty("QuadraKills")
    private long quadraKills;
    @JsonProperty("PentaKills")
    private long pentaKills;
    @JsonProperty("AssistedKills")
    private long assists;
    @JsonProperty("CampsCleared")
    private long clearedCamps;
    @JsonProperty("Deaths")
    private long deaths;
    @JsonProperty("FireGiantKills")
    private long fireGiantKills;
    @JsonProperty("FirstBloods")
    private long firstBloods;
    @JsonProperty("GoldFuryKills")
    private long goldFuryKills;
    @JsonProperty("Id")
    private long id;
    @JsonProperty("MinionKills")
    private long minionKills;
    @JsonProperty("Name")
    private String username;
    @JsonProperty("PhoenixKills")
    private long phoenixKills;
    @JsonProperty("PlayerKills")
    private long kills;
    @JsonProperty("SiegeJuggernautKills")
    private long siegeJuggernautKills;
    @JsonProperty("TowerKills")
    private long towerDestroyed;
    @JsonProperty("WildJuggernautKills")
    private long wildJuggernautKills;
}
