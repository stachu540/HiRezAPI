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

    @JsonProperty("Ability1")
    private final String ability1Name;
    @JsonProperty("Ability2")
    private final String ability2Name;
    @JsonProperty("Ability3")
    private final String ability3Name;
    @JsonProperty("Ability4")
    private final String ability4Name;
    @JsonProperty("Ability5")
    private final String ability5Name;

    @JsonProperty("abilityDescription1")
    private final String ability1Description;
    @JsonProperty("abilityDescription2")
    private final String ability2Description;
    @JsonProperty("abilityDescription3")
    private final String ability3Description;
    @JsonProperty("abilityDescription4")
    private final String ability4Description;
    @JsonProperty("abilityDescription5")
    private final String ability5Description;

    @JsonProperty("AbilityId1")
    private final long ability1Id;
    @JsonProperty("AbilityId2")
    private final long ability2Id;
    @JsonProperty("AbilityId3")
    private final long ability3Id;
    @JsonProperty("AbilityId4")
    private final long ability4Id;
    @JsonProperty("AbilityId5")
    private final long ability5Id;

    @JsonProperty("ChampionAbility1_URL")
    private final String ability1URL;
    @JsonProperty("ChampionAbility2_URL")
    private final String ability2URL;
    @JsonProperty("ChampionAbility3_URL")
    private final String ability3URL;
    @JsonProperty("ChampionAbility4_URL")
    private final String ability4URL;
    @JsonProperty("ChampionAbility5_URL")
    private final String ability5URL;

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
    @JsonProperty("latestChampion")
    private final boolean latestChampion;
    @JsonProperty("Lore")
    private final String lore;
    @JsonProperty("Name")
    private final String name;
    @JsonProperty("Name_English")
    private final String nameEnglish;
    @TextToBoolean
    @JsonProperty("OnFreeRotation")
    private final boolean onFreeRotation;
    @TextToBoolean
    @JsonProperty("OnFreeWeeklyRotation")
    private final boolean onFreeWeeklyRotation;
    /**
     * @return only {@code Norse}
     * @deprecated Unused when returns a same value {@code Norse}
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
    public static class Ability {
        @JsonProperty("Description")
        private final String description;
        @JsonProperty("rechargeSeconds")
        private final long rechargeSeconds;
        @JsonProperty("Summary")
        private final String name;
        @JsonProperty("damageType")
        private final String damageType;
        @JsonProperty("Id")
        private final long id;
        @JsonProperty("URL")
        private final String url;
    }
}
