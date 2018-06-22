package hirezapi.paladins.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirezapi.json.AbstractItem;

public class ChampionItem extends AbstractItem {
  @JsonProperty("Description")
  private String description;
  private Long championId;
  private String itemType;
  private int talentRewardLevel;
}
