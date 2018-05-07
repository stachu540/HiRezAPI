package hirezapi.smite.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("itemDescription")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Description implements Serializable {
  private String cooldown;
  private String cost;
  private String description;
  private List<Item> menuItems;
  private List<Item> rankItems;

  public static class Item extends AbstractMap.SimpleImmutableEntry<String, String> {
    public Item(@JsonProperty("description") String key, @JsonProperty("value") String value) {
      super(key, value);
    }
  }
}
