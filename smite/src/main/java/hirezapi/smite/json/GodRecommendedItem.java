package hirezapi.smite.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import hirezapi.json.Model;
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
public class GodRecommendedItem extends Model {
    @JsonProperty("Category")
    private String category;
    @JsonProperty("Item")
    private String item;
    @JsonProperty("Item")
    private String role;
    private long categoryValueId;
    private long godId;
    private String godName;
    private long iconId;
    private long itemId;
    private long roleValueId;
}
