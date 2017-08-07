package pl.stachuofficial.hirezapi.api;

import pl.stachuofficial.hirezapi.HiRezAPI;
import pl.stachuofficial.hirezapi.games.Paladins;
import pl.stachuofficial.hirezapi.games.Smite;

/**
 * @author <a href="damian@stachuofficial.pl">Damian Staszewski</a>
 * @since 1.8
 */
public class Session extends HttpClient {
    private final String platform;
    private final Signature sig;

    public Session(Smite.Platform platform, String devId, String authKey) {
        super(platform.getUrl());
        this.platform = "SMITE_" + platform.name();
        this.sig = new Signature(platform, devId, authKey);
        if (!HiRezAPI.sessions.containsKey(this.platform)) this.generateSession();
        if (!testSession().toString().startsWith("\"This was a successful test with the following parameters added:")) this.generateSession();
    }

    public Session(Paladins.Platform platform, String devId, String authKey) {
        super(platform.getUrl());
        this.platform = "PALADINS_" + platform.name();
        this.sig = new Signature(platform, devId, authKey);
        if (!HiRezAPI.sessions.containsKey(this.platform)) this.generateSession();
        if (!testSession().toString().startsWith("\"This was a successful test with the following parameters added:")) this.generateSession();
    }

    public StringData get(String endpoint, String... args) {
        return new StringData(request(this.sig.generateUrl(endpoint), args));
    }

    public StringData generateSession() {
        StringData data = new StringData(request(this.sig.createSession()));
        try {
            if (data.toJsonObject().getString("ret_msg").equals("Approved")) {
                HiRezAPI.sessions.setProperty(this.platform, data.toJsonObject().getString("session_id"));
            } else throw new SessionException(data.toJsonObject().getString("ret_msg"));
        } catch (SessionException ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public String testSession() {
        return get("testsession").toString();
    }

    public StringData getDataUsage() {
        return get("getdataused");
    }
}
