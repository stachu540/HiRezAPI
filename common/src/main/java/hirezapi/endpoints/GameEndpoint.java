package hirezapi.endpoints;

import hirezapi.HiRezApi;
import hirezapi.endpoints.statuspage.ServerStatus;
import hirezapi.enums.Language;
import hirezapi.json.AbstractHero;
import hirezapi.json.AbstractItem;
import hirezapi.json.AbstractSkin;
import hirezapi.json.PatchInfo;

import java.util.List;

public abstract class GameEndpoint<H extends AbstractHero,
      S extends AbstractSkin,
      I extends AbstractItem>
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

  public abstract List<S> getSkins(long id, Language language);

  public abstract List<I> getItems(Language language);

  public PatchInfo getPatchInfo() {
    return api.getRestController().request(buildUrl("getpatchinfo"), PatchInfo.class);
  }
}
