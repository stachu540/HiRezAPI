package hirez.api;

import lombok.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.TimeZone;

@Getter
@Setter
@NoArgsConstructor
public class ConfigurationBuilder {
    private String devId;
    private String authKey;
    private BaseEndpoint baseEndpoint;
    private SessionStorage sessionStorage = SessionStorage.DEFAULT;
    private String userAgent = RestClient.DEFAULT_USER_AGENT;
    private Language language = Language.English;

    public ConfigurationBuilder(Configuration configuration) {
        this.devId = configuration.getDevId();
        this.authKey = configuration.getAuthKey();
        this.baseEndpoint = configuration.getBaseEndpoint();
        this.sessionStorage = configuration.getSessionStorage();
        this.userAgent = configuration.getUserAgent();
        this.language = configuration.getLanguage();
    }

    public Configuration build() {
        return new Configuration(this);
    }
}
