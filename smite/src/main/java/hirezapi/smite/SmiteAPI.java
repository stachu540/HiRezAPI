package hirezapi.smite;

import hirezapi.Configuration;
import hirezapi.smite.enums.Smite;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SmiteAPI {
    private final Smite platform;

    public static SmiteAPI of(Smite platform) {
        return new SmiteAPI(platform);
    }

    public SmiteGame init(String devId, String authKey) {
        return new SmiteGame(Configuration.of(platform, devId, authKey));
    }
}
