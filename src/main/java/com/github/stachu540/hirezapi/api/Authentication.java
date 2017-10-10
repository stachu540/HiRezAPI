package com.github.stachu540.hirezapi.api;

import com.github.stachu540.hirezapi.HiRezAPI;
import com.github.stachu540.hirezapi.annotations.Endpoint;
import com.github.stachu540.hirezapi.enums.DataType;
import com.github.stachu540.hirezapi.enums.url.BasePlatform;
import com.github.stachu540.hirezapi.exception.SessionException;
import com.github.stachu540.hirezapi.models.TestSession;
import com.github.stachu540.hirezapi.models.json.CreateSession;
import com.github.stachu540.hirezapi.models.json.Model;
import com.github.stachu540.hirezapi.util.RestClient;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

@Getter
@Setter
public class Authentication <T extends BasePlatform, H extends HiRez<T>>{
    private final String DEV_ID;
    private final String AUTH_KEY;
    private String SESSION_KEY;

    @Getter(AccessLevel.NONE)
    private final H api;

    private final T platform;
    private DataType dataType = DataType.JSON;

    private final RestClient restClient = new RestClient(this);

    public Authentication(HiRezAPI main, T base_platform, H api) {
        this.DEV_ID = main.getDevId();
        this.AUTH_KEY = main.getAuthKey();
        this.platform = base_platform;
        this.api = api;
    }

    public String getUrl(String endpoint, String... args) {
        return String.format("%s/%s%s", platform.getUrl(), getEndpoint(endpoint), (args.length > 0) ? "/" + String.join("/", args) : "");
    }

    public boolean hasSessionKey() {
        return SESSION_KEY != null;
    }

    <O extends Object> O get(String endpoint, Class<O> classModel, String... args) {
        O objectData = restClient.request(endpoint, classModel, args);
        if (objectData instanceof Model) {
            try {
                Model model = (Model) objectData;
                if (model.getServerMessage() != null && !endpoint.equals("createsession"))
                    throw new SessionException(model.getServerMessage());
            } catch (SessionException e) {
                e.printStackTrace();
            }
        }
        return restClient.request(endpoint, classModel, args);
    }

    <T extends Model> T get(Class<T> classModel, String... args) {
        String endpoint = classModel.getDeclaredAnnotation(Endpoint.class).value();
        if (endpoint == null) throw new NullPointerException(String.format("Missing annotation value for class: %s [full_path:%s]", classModel.getSimpleName(), classModel.getCanonicalName()));
        return get(endpoint, classModel, args);
    }

    private void createSession() {
        CreateSession session = api.createSession();
        if (session.getRetMsg().equals("Approved")) {
            SESSION_KEY = session.getSessionId();
        }
    }

    private boolean testSession() {
        TestSession session = api.testSession();
        return session.isSucessful();
    }

    private String getEndpoint(String endpoint) {
        String base_endpoint = String.format("%s%s", endpoint, dataType.name().toLowerCase());
        String sig = getSignatue(endpoint);
        switch (endpoint) {
            case "ping":
                return base_endpoint;
            case "createsession":
                return String.format("%s/%s/%s/%s", base_endpoint, DEV_ID, getSignatue(endpoint), getTimestamp());
            default:
                if (hasSessionKey() && (endpoint.equals("testsession") || testSession())) {
                    return String.format("%s/%s/%s/%s/%s", base_endpoint, DEV_ID, getSignatue(endpoint), SESSION_KEY, getTimestamp());
                } else {
                    createSession();
                    return getEndpoint(endpoint);
                }
        }
    }

    private String getSignatue(String endpoint) {
        try {
            String sig = DEV_ID + endpoint + AUTH_KEY + getTimestamp();
            StringBuilder sb = new StringBuilder();

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sig.getBytes());
            byte bytes[] = md.digest();

            for (byte bit : bytes) {
                String hex = Integer.toHexString(0xff & bit);
                if (hex.length() == 1) sb.append("0");
                sb.append(hex);
            }
            return sb.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private String getTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sdf.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));
        return sdf.format(new Date());
    }
}