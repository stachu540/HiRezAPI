package hirezapi.smite;

import hirezapi.Configuration;
import hirezapi.HiRezApi;
import hirezapi.smite.endpoint.SmiteEndpoint;

public class SmiteGame extends HiRezApi<SmiteEndpoint> {
    SmiteGame(Configuration config) {
        super(config);
    }

    @Override
    public SmiteEndpoint gameEndpoint() {
        return new SmiteEndpoint(this);
    }
}
