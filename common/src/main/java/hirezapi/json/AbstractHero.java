package hirezapi.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
public class AbstractHero<A extends Ability<?>> extends Model {
  @JsonProperty("Ability_1")
  private A ability1;
  @JsonProperty("Ability_2")
  private A ability2;
  @JsonProperty("Ability_3")
  private A ability3;
  @JsonProperty("Ability_4")
  private A ability4;
  @JsonProperty("Ability_5")
  private A ability5;

  @JsonProperty("Pros")
  private String pros;
  @JsonProperty("Cons")
  private String cons;

  @JsonProperty("Health")
  private float health;
  @JsonProperty("Lore")
  private String lore;
  @JsonProperty("Name")
  private String name;
  @JsonProperty("OnFreeRotation")
  @JsonDeserialize(using = BooleanTextDeserializer.class)
  private boolean freeRotation;
  @JsonProperty("Roles")
  private String roles;
  @JsonProperty("Speed")
  private float speed;
  @JsonProperty("Title")
  private String title;
  private Long id;
}
