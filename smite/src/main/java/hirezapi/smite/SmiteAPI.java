package hirezapi.smite;

import hirezapi.AbstractAPI;
import hirezapi.smite.enums.Smite;

import java.util.Objects;

public class SmiteAPI extends AbstractAPI {
    private SmiteAPI(Smite platform) {
        super(platform);
    }

    public static SmiteAPI of(Smite platform) {
        return new SmiteAPI(platform);
    }

    public SmiteGame init(String devId, String authKey) {
        return new SmiteGame(buildConfiguration(Objects.requireNonNull(devId, "DEV_ID"), Objects.requireNonNull(authKey, "AUTH_KEY")), getSessionStorage());
    }
}
