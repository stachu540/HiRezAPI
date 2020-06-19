package hirez.smite.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

@Data
public class Achievements implements ReturnedMessage {
    @JsonProperty("AssistedKills")
    private final int assistedKills;
    @JsonProperty("CampsCleared")
    private final int campsCleared;
    @JsonProperty("Deaths")
    private final int deaths;
    @JsonProperty("DivineSpree")
    private final int divineSpree;
    @JsonProperty("DoubleKills")
    private final int doubleKills;
    @JsonProperty("FireGiantKills")
    private final int fireGiantKills;
    @JsonProperty("FirstBloods")
    private final int firstBloods;
    @JsonProperty("GodLikeSpree")
    private final int godLikeSpree;
    @JsonProperty("GoldFuryKills")
    private final int goldFuryKills;
    @JsonProperty("Id")
    private final long id;
    @JsonProperty("ImmortalSpree")
    private final int immortalSpree;
    @JsonProperty("KillingSpree")
    private final int killingSpree;
    @JsonProperty("MinionKills")
    private final int minionKills;
    @JsonProperty("Name")
    private final String name;
    @JsonProperty("PentaKills")
    private final int pentaKills;
    @JsonProperty("PhoenixKills")
    private final int phoenixKills;
    @JsonProperty("PlayerKills")
    private final int playerKills;
    @JsonProperty("QuadraKills")
    private final int quadraKills;
    @JsonProperty("RampageSpree")
    private final int rampageSpree;
    @JsonProperty("ShutdownSpree")
    private final int shutdownSpree;
    @JsonProperty("SiegeJuggernautKills")
    private final int siegeJuggernautKills;
    @JsonProperty("TowerKills")
    private final int towerKills;
    @JsonProperty("TripleKills")
    private final int tripleKills;
    @JsonProperty("UnstoppableSpree")
    private final int unstoppableSpree;
    @JsonProperty("WildJuggernautKills")
    private final int wildJuggernautKills;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
}
