package hirezapi.smite.endpoint;

import hirezapi.HiRezApi;
import hirezapi.endpoints.GameEndpoint;
import hirezapi.enums.Language;
import hirezapi.smite.json.God;
import hirezapi.smite.json.GodItem;
import hirezapi.smite.json.GodRecommendedItem;
import hirezapi.smite.json.GodSkin;

import java.util.Arrays;
import java.util.List;

public class SmiteEndpoint extends GameEndpoint<God, GodSkin, GodItem> {
  public SmiteEndpoint(HiRezApi api) {
    super(api);
  }

  /**
   * List all available gods.
   * @param language Language code
   * @return List all in game gods
   */
  public List<God> getGods(Language language) {
    return Arrays.asList(api.getRestController()
          .request(buildUrl("getgods",
                Integer.toString(language.getId())), God[].class));
  }

  /**
   * Getting Recommended items for specific god.
   * @param god the god
   * @param language Language Code
   * @return List all recommended items for specific god
   */
  public List<GodRecommendedItem> getRecommendedItems(God god, Language language) {
    return Arrays.asList(api.getRestController()
          .request(buildUrl("getgodrecommendeditems",
                Long.toString(god.getId()),
                Integer.toString(language.getId())), GodRecommendedItem[].class));
  }

  @Override
  public List<GodSkin> getSkins(long id, Language language) {
    return Arrays.asList(api.getRestController()
          .request(buildUrl("getgodskins",
                Long.toString(id),
                Integer.toString(language.getId())), GodSkin[].class));
  }

  @Override
  public List<GodItem> getItems(Language language) {
    return Arrays.asList(api.getRestController()
          .request(buildUrl("getitems",
                Integer.toString(language.getId())), GodItem[].class));
  }
}
