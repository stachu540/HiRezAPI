package hirezapi.smite.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import hirezapi.json.AbstractHero;
import hirezapi.json.deserializer.BooleanTextDeserializer;
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
public class God extends AbstractHero<GodAbility> {
  @JsonProperty("AttackSpeed")
  private float attackSpeed;
  @JsonProperty("AttackSpeedPerLevel")
  private float attackSpeedPerLevel;
  @JsonProperty("HP5PerLevel")
  private float hp5PerLevel;
  @JsonProperty("HealthPerFive")
  private float hp5;
  @JsonProperty("HealthPerLevel")
  private float healthPerLevel;
  @JsonProperty("MP5PerLevel")
  private float mp5PerLevel;
  @JsonProperty("MagicProtection")
  private float magicProtection;
  @JsonProperty("MagicProtectionPerLevel")
  private float magicProtectionPerLevel;
  @JsonProperty("MagicalPower")
  private float magicalPower;
  @JsonProperty("MagicalPowerPerLevel")
  private float magicalPowerPerLevel;
  @JsonProperty("Mana")
  private float mana;
  @JsonProperty("ManaPerFive")
  private float mp5;
  @JsonProperty("ManaPerLevel")
  private float manaPerLevel;
  @JsonProperty("Pantheon")
  private String pantheon;
  @JsonProperty("PhysicalPower")
  private float physicalPower;
  @JsonProperty("PhysicalPowerPerLevel")
  private float physicalPowerPerLevel;
  @JsonProperty("PhysicalProtection")
  private float physicalProtection;
  @JsonProperty("PhysicalProtectionPerLevel")
  private float physicalProtectionPerLevel;
  @JsonProperty("Type")
  private String type;
  @JsonProperty("basicAttack")
  private Description basicAttack;
  @JsonProperty("latestGod")
  @JsonDeserialize(using = BooleanTextDeserializer.class)
  private boolean latestGod;
}
