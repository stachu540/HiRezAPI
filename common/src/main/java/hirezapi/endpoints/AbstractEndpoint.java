package hirezapi.endpoints;

import hirezapi.HiRezApi;
import hirezapi.Platform;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

@RequiredArgsConstructor
public abstract class AbstractEndpoint {
    protected final HiRezApi api;

    protected String buildUrl(String endpoint, String... queryParams) {
        final String realEndpoint = "/" + ((!endpoint.toLowerCase().endsWith("json")) ? endpoint + "json" : endpoint);
        final String timestamp = getTimestamp();

        switch (endpoint.toLowerCase()) {
            case "ping":
                return realEndpoint;
            case "createsession":
                return String.format("%s/%s/%s/%s", realEndpoint,
                        api.getConfiguration().getDevId(),
                        generateSignature(endpoint, timestamp),
                        timestamp);
            default:
                if (hasSession(api.getConfiguration().getPlatform()) && (endpoint.equalsIgnoreCase("testsession") || api.sessionEndpoint().test().isSuccessful())) {
                    return String.format("%s/%s/%s/%s", realEndpoint,
                            api.getConfiguration().getDevId(),
                            generateSignature(endpoint, timestamp),
                            timestamp)
                            + ((queryParams.length > 0) ? "/" + String.join("/", queryParams) : "");
                } else {
                    synchronized (api.sessionEndpoint().create()) {
                        return buildUrl(endpoint, queryParams);
                    }
                }
        }
    }

    private boolean hasSession(Platform platform) {
        return api.sessionEndpoint().getSessionStorage().contains(platform);
    }

    private String generateSignature(String endpoint, String timestamp) {
        String templateSignature = api.getConfiguration().getDevId() + endpoint + api.getConfiguration().getAuthKey() + timestamp;
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
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return signatureBuilder.toString();
    }

    private String getTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sdf.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));
        return sdf.format(new Date());
    }
}
