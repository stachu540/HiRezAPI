package hirezapi.smite.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import hirezapi.json.AbstractItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GodItem extends AbstractItem {
    @JsonProperty("ChildItemId")
    private long childItemId;
    @JsonProperty("ItemDescription")
    private Description itemDescription;
    @JsonProperty("ItemTier")
    private int itemTier;
    @JsonProperty("Price")
    private int price;
    @JsonProperty("RootItemId")
    private long rootItemId;
    @JsonProperty("ShortDesc")
    private String shortDescription;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("StartingItem")
    private boolean startingItem;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class Description {
        private String description;
        private List<hirezapi.smite.json.Description.Item> menuItems;
        private String secondaryDescription;
    }
}
