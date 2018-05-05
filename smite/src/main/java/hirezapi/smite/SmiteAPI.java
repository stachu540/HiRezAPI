package hirezapi.smite;

import hirezapi.Configuration;
import hirezapi.rest.RestClient;
import hirezapi.smite.enums.Smite;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Setter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SmiteAPI {
    private final Smite platform;
    private RestTemplate restClient;

    public static SmiteAPI of(Smite platform) {
        return new SmiteAPI(platform);
    }

    public SmiteGame init(String devId, String authKey) {
        if (Objects.isNull(restClient)) {
            this.restClient = new RestClient(platform).getRestClient();
        }

        return new SmiteGame(Configuration.of(platform, devId, authKey), restClient);
    }
}
