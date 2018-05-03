package hirezapi.paladins;

import hirezapi.Configuration;
import hirezapi.HiRezApi;
import hirezapi.paladins.endpoint.PaladinsEndpoint;

public class PaladinsGame extends HiRezApi<PaladinsEndpoint> {
    PaladinsGame(Configuration configuration) {
        super(configuration);
    }

    @Override
    public PaladinsEndpoint gameEndpoint() {
        return new PaladinsEndpoint(this);
    }
}
