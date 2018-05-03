package hirezapi.paladins;

import hirezapi.Configuration;
import hirezapi.paladins.enums.Paladins;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PaladinsAPI {
    private final Paladins platform;

    public static PaladinsAPI of(Paladins platform) {
        return new PaladinsAPI(platform);
    }

    public PaladinsGame init(String devId, String authKey) {
        return new PaladinsGame(Configuration.of(platform, devId, authKey));
    }
}
