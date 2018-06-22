package hirezapi.endpoints;

import hirezapi.HiRezApi;
import hirezapi.endpoints.statuspage.ServerStatus;
import hirezapi.enums.Language;
import hirezapi.enums.Queue;
import hirezapi.json.*;

import java.util.List;

public abstract class GameEndpoint<H extends AbstractHero,
      S extends AbstractSkin,
      I extends AbstractItem,
      PS extends PlayerStats>
      extends AbstractEndpoint {

  public GameEndpoint(HiRezApi api) {
    super(api);
  }

  public ServerStatus serverStatus() {
    return new ServerStatus(api);
  }

  public List<S> getSkins(H hero, Language language) {
    return getSkins(hero.getId(), language);
  }

  public abstract List<S> getSkins(Long id, Language language);

  public abstract List<I> getItems(Language language);

  public PatchInfo getPatchInfo() {
    return api.getRestController().request(buildUrl("getpatchinfo"), PatchInfo.class);
  }

  public abstract List<PS> getQueueStats(String username, Queue queue);
}
