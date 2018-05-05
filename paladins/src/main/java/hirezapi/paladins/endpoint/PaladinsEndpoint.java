package hirezapi.paladins.endpoint;

import hirezapi.HiRezApi;
import hirezapi.endpoints.GameEndpoint;
import hirezapi.enums.Language;
import hirezapi.json.AbstractHero;

import java.util.List;

public class PaladinsEndpoint extends GameEndpoint {
    public PaladinsEndpoint(HiRezApi api) {
        super(api);
    }

    @Override
    public List getSkins(long id, Language language) {
        return null;
    }

    @Override
    public List getItems(Language language) {
        return null;
    }
}
