package hirezapi.paladins;

import hirezapi.Configuration;
import hirezapi.paladins.enums.Paladins;
import hirezapi.rest.RestClient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Setter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PaladinsAPI {
    private final Paladins platform;
    private RestTemplate restClient;

    public static PaladinsAPI of(Paladins platform) {
        return new PaladinsAPI(platform);
    }

    public PaladinsGame init(String devId, String authKey) {
        if (Objects.isNull(restClient)) {
            this.restClient = new RestClient(platform).getRestClient();
        }

        return new PaladinsGame(Configuration.of(platform, devId, authKey), restClient);
    }
}
