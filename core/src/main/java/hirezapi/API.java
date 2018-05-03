package hirezapi;

import hirezapi.paladins.PaladinsAPI;
import hirezapi.paladins.enums.Paladins;
import hirezapi.smite.SmiteAPI;
import hirezapi.smite.enums.Smite;

public class API {
    public static SmiteAPI smite(Smite platform) {
        return SmiteAPI.of(platform);
    }

    public static PaladinsAPI paladins(Paladins platform) {
        return PaladinsAPI.of(platform);
    }
}
