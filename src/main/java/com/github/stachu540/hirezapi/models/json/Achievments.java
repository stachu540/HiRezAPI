package com.github.stachu540.hirezapi.models.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.stachu540.hirezapi.annotations.Endpoint;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonIgnoreProperties(ignoreUnknown = false)
@EqualsAndHashCode(callSuper = true)
@Endpoint("getplayerachievements")
public class Achievments extends Model {
    @JsonProperty("AssistedKills")			private long assists;
    @JsonProperty("CampsCleared")			private long clearedCamps;
    @JsonProperty("Deaths")					private long deaths;
    @JsonProperty("FireGiantKills")			private long fireGigantKills;
    @JsonProperty("FirstBloods")			private long firstBloods;
    @JsonProperty("GoldFuryKills")			private long goldFuryKills;
    @JsonProperty("Id")						private long id;
    @JsonProperty("MinionKills")			private long minionKills;
    @JsonProperty("Name")					private String username;
    @JsonProperty("PhoenixKills")			private long phoenixKills;
    @JsonProperty("PlayerKills")			private long kills;
    @JsonProperty("SiegeJuggernautKills")	private long siegeJuggernautKills;
    @JsonProperty("TowerKills")				private long towerDestroyed;
    @JsonProperty("WildJuggernautKills")	private long wildJuggernautKills;

    @JsonProperty("KillingSpree")       private final long killingSprees;
    @JsonProperty("RampageSpree")       private final long rampages;
    @JsonProperty("UnstoppableSpree")   private final long unstioppables;
    @JsonProperty("DivineSpree")        private final long divines;
    @JsonProperty("ImmortalSpree")      private final long immortals;
    @JsonProperty("GodLikeSpree")       private final long godlikes;
    @JsonProperty("ShutdownSpree")      private final long shutdowns;

    @JsonProperty("DoubleKills")    private final long dobuleKills;
    @JsonProperty("TripleKills")    private final long tripleKills;
    @JsonProperty("QuadraKills")    private final long quadraKills;
    @JsonProperty("PentaKills")     private final long pentaKills;
}
