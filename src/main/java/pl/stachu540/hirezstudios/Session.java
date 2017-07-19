package pl.stachu540.hirezstudios;

import pl.stachu540.HiRezAPI;
import pl.stachu540.util.HttpClient;
import pl.stachu540.util.StringData;
import pl.stachu540.util.Utility;

public class Session extends HttpClient {
    private final String platform;
    private final String devId;
    private final String authKey;

    Session(Smite.Platform platform, String devId, String authKey) {
        super(platform.getUrl());
        this.platform = "SMITE_" + platform.name();
        this.devId = devId;
        this.authKey = authKey;
        if (!HiRezAPI.sessions.containsKey(this.platform)) this.generateSession();
        if (!testSession().toString().startsWith("\"This was a successful test with the following parameters added:")) this.generateSession();
    }

    Session(Paladins.Platform platform, String devId, String authKey) {
        super(platform.getUrl());
        this.platform = "PALADINS_" + platform.name();
        this.devId = devId;
        this.authKey = authKey;
        if (!HiRezAPI.sessions.containsKey(this.platform)) this.generateSession();
        if (!testSession().toString().startsWith("\"This was a successful test with the following parameters added:")) this.generateSession();
    }

    StringData get(String endpoint, String... args) {
        return new StringData(request(Utility.generateUrl(this.platform, endpoint, devId, authKey), args));
    }

    void generateSession() {
        try {
            StringData data = new StringData(request("createsessionJson", devId, Utility.generateSignature(devId, authKey, "createsession"), Utility.generateTimestamp()));
            if (data.toJsonObject().getString("ret_msg").equals("Approved")) {
                HiRezAPI.sessions.setProperty(this.platform, data.toJsonObject().getString("session_id"));
            } else throw new Exception(data.toJsonObject().getString("ret_msg"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    String testSession() {
        return get("testsession").toString();
    }

    StringData getDataUsage() {
        return get("getdataused");
    }
}
