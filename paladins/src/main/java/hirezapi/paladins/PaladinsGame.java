package hirezapi.paladins;

import hirezapi.Configuration;
import hirezapi.HiRezApi;
import hirezapi.paladins.endpoint.PaladinsEndpoint;
import org.springframework.web.client.RestTemplate;

public class PaladinsGame extends HiRezApi<PaladinsEndpoint> {
    PaladinsGame(Configuration configuration, RestTemplate restClient) {
        super(configuration, restClient);
    }

    @Override
    public PaladinsEndpoint gameEndpoint() {
        return new PaladinsEndpoint(this);
    }
}
