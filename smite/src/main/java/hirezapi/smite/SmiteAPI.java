package hirezapi.smite;

import hirezapi.AbstractAPI;
import hirezapi.Configuration;
import hirezapi.Platform;
import hirezapi.rest.RestClient;
import hirezapi.smite.enums.Smite;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

public class SmiteAPI extends AbstractAPI {
    private SmiteAPI(Smite platform) {
        super(platform);
    }

    public static SmiteAPI of(Smite platform) {
        return new SmiteAPI(platform);
    }

    public SmiteGame init(String devId, String authKey) {
        if (Objects.isNull(getRestClient())) {
            setRestClient(new RestClient(getPlatform()).getRestClient());
        }

        return new SmiteGame(buildConfiguration(Objects.requireNonNull(devId, "DEV_ID"), Objects.requireNonNull(authKey, "AUTH_KEY")), getRestClient(), getSessionStorage());
    }
}
