package pl.stachuofficial.hirezapi.api;

import pl.stachuofficial.hirezapi.HiRezAPI;
import pl.stachuofficial.hirezapi.games.Paladins;
import pl.stachuofficial.hirezapi.games.Smite;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public class Signature {
    private final String devId;
    private final String authKey;
    private final String platform;

    Signature(Smite.Platform platform, String devId, String authKey) {
        this.devId = devId;
        this.authKey = authKey;
        this.platform = "SMITE_" + platform.name();
    }

    Signature(Paladins.Platform platform, String devId, String authKey) {
        this.devId = devId;
        this.authKey = authKey;
        this.platform = "PALADINS_" + platform.name();
    }

    private String generateTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sdf.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));
        return sdf.format(new Date());
    }

    private String generateSignature(String method) {
        try {
            String sig = devId + method + authKey + generateTimestamp();
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

    public String generateUrl(String method) {
        String url = method + "Json/";
        url += devId + "/";
        url += generateSignature(method) + "/";
        url += HiRezAPI.sessions.getProperty(platform) + "/";
        url += generateTimestamp();

        return url;
    }

    public String createSession() {
        String url = "createsessionJson/";
        url += devId + "/";
        url += generateSignature("createsession") + "/";
        url += generateTimestamp();

        return url;
    }
}
