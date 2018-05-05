package hirezapi.smite;

import hirezapi.Configuration;
import hirezapi.HiRezApi;
import hirezapi.session.SessionStorage;
import hirezapi.smite.endpoint.SmiteEndpoint;
import org.springframework.web.client.RestTemplate;

public class SmiteGame extends HiRezApi<SmiteEndpoint> {
    SmiteGame(Configuration config, RestTemplate restClient, SessionStorage sessionStorage) {
        super(config, restClient, sessionStorage);
    }

    @Override
    public SmiteEndpoint gameEndpoint() {
        return new SmiteEndpoint(this);
    }
}
