package hirezapi.paladins.endpoint;

import hirezapi.HiRezApi;
import hirezapi.endpoints.GameEndpoint;
import hirezapi.enums.Language;
import hirezapi.enums.Queue;
import hirezapi.paladins.json.Champion;
import hirezapi.paladins.json.ChampionItem;
import hirezapi.paladins.json.ChampionPlayedStats;
import hirezapi.paladins.json.ChampionSkin;

import java.util.Arrays;
import java.util.List;

public class PaladinsEndpoint extends GameEndpoint<Champion, ChampionSkin, ChampionItem, ChampionPlayedStats> {
  public PaladinsEndpoint(HiRezApi api) {
    super(api);
  }

  @Override
  public List<ChampionSkin> getSkins(Long id, Language language) {
    return Arrays.asList(api.getRestController().request(buildUrl("getqueuestats", id.toString(), language.getId().toString()), ChampionSkin[].class));
  }

  @Override
  public List<ChampionItem> getItems(Language language) {
    return Arrays.asList(api.getRestController().request(buildUrl("getqueuestats", language.getId().toString()), ChampionItem[].class));
  }


  @Override
  public List<ChampionPlayedStats> getQueueStats(String username, Queue queue) {
    return Arrays.asList(api.getRestController()
          .request(buildUrl("getqueuestats",
                username,
                queue.getId().toString()),
                ChampionPlayedStats[].class));
  }
}
