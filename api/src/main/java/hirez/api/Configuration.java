package hirez.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import okhttp3.OkHttpClient;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.TimeZone;
import java.util.function.Consumer;

@Getter
@AllArgsConstructor
public class Configuration {
    private final String devId;
    private final String authKey;
    private final BaseEndpoint baseEndpoint;
    private final SessionStorage sessionStorage;
    private final String userAgent;
    private final Language language;
    @Getter(AccessLevel.PACKAGE)
    private final Consumer<ObjectMapper> mapper;
    @Getter(AccessLevel.PACKAGE)
    private final Consumer<OkHttpClient.Builder> httpClient;

    public Configuration(ConfigurationBuilder cb) {
        this(cb.getDevId(), cb.getAuthKey(), cb.getBaseEndpoint(), cb.getSessionStorage(), cb.getUserAgent(), cb.getLanguage(), cb.getMapper(), cb.getHttpClient());
    }

    public String createUrl(String method, String... args) {
        StringBuilder sb = new StringBuilder(baseEndpoint.getBaseUrl())
                .append("/").append(method).append("json");

        String timestamp = doTimestamp();

        if (!method.equals("ping")) {
            sb.append("/").append(devId);
            sb.append("/").append(doSignature(method, timestamp));
            if (!method.equals("createsession") && sessionStorage.isPresent()) {
                sb.append("/").append(sessionStorage.get());
            } else {
                throw new HiRezException("Require generate session for: " + method);
            }
            sb.append("/").append(timestamp);
            if (args.length > 0) {
                sb.append("/").append(String.join("/", args));
            }
        }
        return sb.toString();
    }


    private String doTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sdf.setTimeZone(TimeZone.getTimeZone(ZoneOffset.UTC));
        return sdf.format(new Date());
    }

    private String doSignature(String method, String timestamp) {
        String templateSignature = new StringBuilder(devId)
                .append(method)
                .append(authKey)
                .append(timestamp)
                .toString();
        StringBuilder signatureBuilder = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(templateSignature.getBytes());
            byte[] bytes = md.digest();

            for (byte bit : bytes) {
                String hex = Integer.toHexString(0xff & bit);
                if (hex.length() == 1) {
                    signatureBuilder.append("0");
                }
                signatureBuilder.append(hex);
            }
        } catch (NoSuchAlgorithmException ignore) {
        }

        return signatureBuilder.toString();
    }

    public ConfigurationBuilder newBuilder() {
        return new ConfigurationBuilder(this);
    }
}
