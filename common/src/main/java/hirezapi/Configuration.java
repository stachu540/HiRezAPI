package hirezapi;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Configuration {
    private final Platform platform;
    private final String devId;
    private final String authKey;

    public static Configuration of(Platform platform, String devId, String authKey) {
        return new Configuration(platform, devId, authKey) ;
    }
}
