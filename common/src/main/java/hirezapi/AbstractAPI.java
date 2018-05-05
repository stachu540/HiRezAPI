package hirezapi;

import hirezapi.session.EnvironmentalSessionStorage;
import hirezapi.session.SessionStorage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.client.RestTemplate;

@Setter
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractAPI<T extends HiRezApi> {
    private final Platform platform;
    private RestTemplate restClient;
    private SessionStorage sessionStorage = new EnvironmentalSessionStorage();

    public abstract T init(String devId, String authKey);

    protected Configuration buildConfiguration(String devId, String authKey) {
        return Configuration.of(platform, devId, authKey);
    }
}
