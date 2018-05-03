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

    private String pros;
    private String cons;

    private float health;
    private String lore;
    private String name;
    private boolean freeRotation;
    private String roles;
    private float speed;
    private String title;
    private long id;
}
