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
public class SimpleTeam extends Model {
    @JsonProperty("Founder")
    private String founder;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Players")
    private int players;
    @JsonProperty("Tag")
    private String tag;
    @JsonProperty("TeamId")
    private long teamId;
}
