package hirez.smite.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

@Data
public class Achievements implements ReturnedMessage {
    private final int wildJuggernautKills;
    private final int goldFuryKills;
    private final int firstBloods;
    private final int immortalSpree;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    private final int assistedKills;
    private final int towerKills;
    private final String name;
    private final int pentaKills;
    private final int siegeJuggernautKills;
    private final int divineSpree;
    private final int killingSpree;
    private final int campsCleared;
    private final int tripleKills;
    private final int minionKills;
    private final int deaths;
    private final int rampageSpree;
    private final int godLikeSpree;
    private final int unstoppableSpree;
    private final int fireGiantKills;
    private final int playerKills;
    private final int phoenixKills;
    private final int shutdownSpree;
    private final int quadraKills;
    private final int doubleKills;
    private final long id;
}
