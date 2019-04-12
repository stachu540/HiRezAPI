package hirez.smite.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import hirez.api.object.adapters.TextToBoolean;
import hirez.api.object.interfaces.IDObject;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

import java.util.List;

@Data
@SuppressWarnings("unused")
public class God implements ReturnedMessage {
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

    private final Description abilityDescription1;
    private final Description abilityDescription2;
    private final Description abilityDescription3;
    private final Description abilityDescription4;
    private final Description abilityDescription5;

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

    private final double attackSpeed;
    private final double attackSpeedPerLevel;
    private final Description basicAttack;
    private final String cons;

    @JsonProperty("godAbility1_URL")
    private final String ability1URL;
    @JsonProperty("godAbility2_URL")
    private final String ability2URL;
    @JsonProperty("godAbility3_URL")
    private final String ability3URL;
    @JsonProperty("godAbility4_URL")
    private final String ability4URL;
    @JsonProperty("godAbility5_URL")
    private final String ability5URL;

    @JsonProperty("godCard_URL")
    private final String cardURL;
    @JsonProperty("godIcon_URL")
    private final String iconURL;

    private final double hp5PerLevel;
    private final int health;
    private final int healthPerFive;
    private final int healthPerLevel;
    private final long id;
    @TextToBoolean
    private final boolean latestGod;
    private final String lore;
    @JsonProperty("MP5PerLevel")
    private final double mp5PerLevel;
    private final int magicProtection;
    private final double magicProtectionPerLevel;
    private final int magicalPower;
    private final int magicalPowerPerLevel;
    private final int mana;
    private final double manaPerFive;
    private final int manaPerLevel;
    private final String name;
    @TextToBoolean
    private final boolean onFreeRotation;
    private final String pantheon;
    private final int physicalPower;
    private final int physicalPowerPerLevel;
    private final int physicalProtection;
    private final int physicalProtectionPerLevel;
    private final String pros;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    private final String roles;
    private final int speed;
    private final String title;
    private final String type;

    @Data
    @SuppressWarnings("unused")
    @JsonRootName("itemDescription")
    public static class Description {
        private final String cooldown;
        private final String cost;
        private final String description;
        @JsonProperty("menuitems")
        private final List<DescriptionValue> menuItems;
        @JsonProperty("rankitems")
        private final List<DescriptionValue> rankItems;
        private final String secondaryDescription;
    }

    @Data
    @SuppressWarnings("unused")
    public static class Ability {
        private final Description description;
        private final long id;
        @JsonProperty("Summary")
        private final String name;
        @JsonProperty("URL")
        private final String url;
    }
}
