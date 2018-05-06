package hirezapi.paladins;

import hirezapi.AbstractAPI;
import hirezapi.paladins.enums.Paladins;

import java.util.Objects;

public class PaladinsAPI extends AbstractAPI {

    private PaladinsAPI(Paladins platform) {
        super(platform);
    }

    public static PaladinsAPI of(Paladins platform) {
        return new PaladinsAPI(platform);
    }

    public PaladinsGame init(String devId, String authKey) {
        return new PaladinsGame(buildConfiguration(Objects.requireNonNull(devId, "DEV_ID"), Objects.requireNonNull(authKey, "AUTH_KEY")), getSessionStorage());
    }
}
