package hirez.smite.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.adapters.TextToBoolean;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

import java.util.List;

@Data
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
    private final ItemDescription abilityDescription1;
    @JsonProperty("abilityDescription2")
    private final ItemDescription abilityDescription2;
    @JsonProperty("abilityDescription3")
    private final ItemDescription abilityDescription3;
    @JsonProperty("abilityDescription4")
    private final ItemDescription abilityDescription4;
    @JsonProperty("abilityDescription5")
    private final ItemDescription abilityDescription5;

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

    @JsonProperty("AttackSpeed")
    private final double attackSpeed;
    @JsonProperty("AttackSpeedPerLevel")
    private final double attackSpeedPerLevel;
    @JsonProperty("basicAttack")
    private final ItemDescription basicAttack;
    @JsonProperty("Cons")
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

    @JsonProperty("HP5PerLevel")
    private final double hp5PerLevel;
    @JsonProperty("Health")
    private final int health;
    @JsonProperty("HealthPerFive")
    private final int healthPerFive;
    @JsonProperty("HealthPerLevel")
    private final int healthPerLevel;
    private final long id;
    @TextToBoolean
    @JsonProperty("latestGod")
    private final boolean latestGod;
    @JsonProperty("Lore")
    private final String lore;
    @JsonProperty("MP5PerLevel")
    private final double mp5PerLevel;
    @JsonProperty("MagicProtection")
    private final int magicProtection;
    @JsonProperty("MagicProtectionPerLevel")
    private final double magicProtectionPerLevel;
    @JsonProperty("MagicalPower")
    private final int magicalPower;
    @JsonProperty("MagicalPowerPerLevel")
    private final int magicalPowerPerLevel;
    @JsonProperty("Mana")
    private final int mana;
    @JsonProperty("ManaPerFive")
    private final double manaPerFive;
    @JsonProperty("ManaPerLevel")
    private final int manaPerLevel;
    @JsonProperty("Name")
    private final String name;
    @TextToBoolean
    @JsonProperty("OnFreeRotation")
    private final boolean onFreeRotation;
    @JsonProperty("Pantheon")
    private final String pantheon;
    @JsonProperty("PhysicalPower")
    private final int physicalPower;
    @JsonProperty("PhysicalPowerPerLevel")
    private final int physicalPowerPerLevel;
    @JsonProperty("PhysicalProtection")
    private final int physicalProtection;
    @JsonProperty("PhysicalProtectionPerLevel")
    private final int physicalProtectionPerLevel;
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
    public static class ItemDescription {
        @JsonProperty("itemDescription")
        private final Description itemDescription;
    }

    @Data
    public static class Ability {
        @JsonProperty("Description")
        private final ItemDescription description;
        @JsonProperty("Id")
        private final long id;
        @JsonProperty("Summary")
        private final String name;
        @JsonProperty("URL")
        private final String url;
    }
}
