package pl.stachu540.hirezstudios.instance;

import org.json.JSONObject;
import pl.stachu540.hirezstudios.util.Endpoints;
import pl.stachu540.util.API;
import pl.stachu540.util.MD5Hash;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

/**
 * @author Damian Staszewski <damian@stachuofficial.pl>
 * @since 1.8
 */
public class Session {

    private final Endpoints endpoints;
    private final String devId;
    private final String authKey;
    private final String timestamp = timeStamp();

    private String timeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sdf.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));
        return sdf.format(new Date());
    }

    private final int session_time = 1000 * 60 * 15;

    public Session(String devId, String authKey, Endpoints endpoints) {
        this.devId = devId;
        this.authKey = authKey;
        this.endpoints = endpoints;
    }

    public String getUrl() {
        return endpoints.url();
    }

    public String getTimestamp() { return timestamp; }

    public MD5Hash generateSig(String method) throws NoSuchAlgorithmException {
        if (method.toLowerCase().endsWith("json")) method = method.substring(0, method.length() - 4);
        return new MD5Hash(devId + method + authKey + getTimestamp());
    }

    public String createSession(MD5Hash signature) throws Exception {
        API api = new API(endpoints.url());
        String url = "/createsessionJson/" + devId + "/" + signature.getHash() + "/" + timestamp;
        JSONObject data = api.getData(API.requestType.GET, url);
        JSONObject content = new JSONObject();
        if (data.getBoolean("_success")) content = data.getJSONObject("_content");
        if (content.getString("ret_msg").equals("Approved")) return content.getString("session_id");
        else {
            Exception exception = (Exception) Class.forName(data.getString("_exception")).getConstructor(String.class, Throwable.class).newInstance(data.getString("_exceptionMessage"));
            throw exception;
        }
    }

}

