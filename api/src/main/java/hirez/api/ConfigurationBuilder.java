package hirez.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import okhttp3.OkHttpClient;

import java.util.function.Consumer;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ConfigurationBuilder {
    private String devId;
    private String authKey;
    private BaseEndpoint baseEndpoint;
    private SessionStorage sessionStorage = SessionStorage.DEFAULT;
    private String userAgent = Endpoint.DEFAULT_USER_AGENT;
    private Language language = Language.English;
    @Setter(AccessLevel.NONE)
    private Consumer<ObjectMapper> mapper = ($) -> {};
    @Setter(AccessLevel.NONE)
    private Consumer<OkHttpClient.Builder> httpClient = ($) -> {};

    ConfigurationBuilder(Configuration configuration) {
        this.devId = configuration.getDevId();
        this.authKey = configuration.getAuthKey();
        this.baseEndpoint = configuration.getBaseEndpoint();
        this.sessionStorage = configuration.getSessionStorage();
        this.userAgent = configuration.getUserAgent();
        this.language = configuration.getLanguage();
    }

    public ConfigurationBuilder mapper(Consumer<ObjectMapper> mapper) {
        this.mapper = this.mapper.andThen(mapper);
        return this;
    }

    public ConfigurationBuilder httpClient(Consumer<OkHttpClient.Builder> httpClient) {
        this.httpClient = this.httpClient.andThen(httpClient);
        return this;
    }

    public ConfigurationBuilder applyFrom(Consumer<ConfigurationBuilder> configuration) {
        configuration.accept(this);
        return this;
    }

    public Configuration build() {
        return new Configuration(this);
    }
}
