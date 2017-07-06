package pl.stachu540.hirezstudios;

import pl.stachu540.hirezstudios.util.Endpoints;

/**
 * @author Damian Staszewski <damian@stachuofficial.pl>
 * @since 1.8
 */
public class HiRezAPI {
    private final String devId;
    private final String authKey;
    private final Endpoints endpoint;

    public HiRezAPI(String devId, String authKey, Endpoints endpoint) {
        this.devId = devId;
        this.authKey = authKey;
        this.endpoint = endpoint;
    }
}
