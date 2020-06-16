package hirez.paladins.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.adapters.TextToBoolean;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

@Data
public class Champion implements ReturnedMessage {
    @JsonProperty("Ability_1")
    private final Ability ability1;
    @JsonProperty("Ability_2")
    private final Ability ability2;
    @JsonProperty("Ability_3")
    private final Ability ability3;
    @JsonProperty("Ability_4")
    private final Ability ability4;
    @JsonProperty("Ability_5")
    private final Ability ability5;

    private final String abilityDescription1;
    private final String abilityDescription2;
    private final String abilityDescription3;
    private final String abilityDescription4;
    private final String abilityDescription5;

    @JsonProperty("AbilityId1")
    private final long abilityId1;
    @JsonProperty("AbilityId2")
    private final long abilityId2;
    @JsonProperty("AbilityId3")
    private final long abilityId3;
    @JsonProperty("AbilityId4")
    private final long abilityId4;
    @JsonProperty("AbilityId5")
    private final long abilityId5;

    @JsonProperty("ChampionAbility1_URL")
    private final String championAbility1URL;
    @JsonProperty("ChampionAbility2_URL")
    private final String championAbility2URL;
    @JsonProperty("ChampionAbility3_URL")
    private final String championAbility3URL;
    @JsonProperty("ChampionAbility4_URL")
    private final String championAbility4URL;
    @JsonProperty("ChampionAbility5_URL")
    private final String championAbility5URL;

    @JsonProperty("ChampionCard_URL")
    private final String championCardURL;
    @JsonProperty("ChampionIcon_URL")
    private final String championIconURL;

    @JsonProperty("Cons")
    private final String cons;
    @JsonProperty("Health")
    private final int health;
    private final long id;
    @TextToBoolean
    private final boolean latestChampion;
    @JsonProperty("Lore")
    private final String lore;
    @JsonProperty("Name")
    private final String name;
    @TextToBoolean
    @JsonProperty("OnFreeRotation")
    private final boolean onFreeRotation;
    @TextToBoolean
    @JsonProperty("OnFreeWeeklyRotation")
    private final boolean onFreeWeeklyRotation;
    /**
     * @deprecated Unused when returns a same value {@code Norse}
     * @return only {@code Norse}
     */
    @Deprecated
    @JsonProperty("Pantheon")
    private final String pantheon;
    @JsonProperty("Pros")
    private final String pros;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    @JsonProperty("Roles")
    private final String roles;
    @JsonProperty("Speed")
    private final int speed;
    @JsonProperty("Title")
    private final String title;
    @JsonProperty("Type")
    private final String type;

    @Data
    public class Ability {
        @JsonProperty("Description")
        private final String description;
        private final long rechargeSeconds;
        @JsonProperty("Summary")
        private final String name;
        @JsonProperty("Id")
        private final long id;
        @JsonProperty("URL")
        private final String url;
    }
}
