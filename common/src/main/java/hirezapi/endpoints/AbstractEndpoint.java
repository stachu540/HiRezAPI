package hirezapi.endpoints;

import hirezapi.HiRezApi;
import lombok.RequiredArgsConstructor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

@RequiredArgsConstructor
public abstract class AbstractEndpoint<T extends HiRezApi> {
    protected final T api;

    protected String buildUrl(String endpoint, String... queryParams) {
        final String realEndpoint = "/" + ((!endpoint.toLowerCase().endsWith("json")) ?
            endpoint + "json" : endpoint);
        if (endpoint.equalsIgnoreCase("ping")) {
            return realEndpoint;
        }

        String end = getTimestamp();
        if (!endpoint.equals("createsession")) {
            String sessionId = api.sessionEndpoint().getSessionStorage().get(api.getConfiguration().getPlatform());
            if (sessionId == null || !api.sessionEndpoint().test().isSuccessful()) {
                sessionId = api.sessionEndpoint().create().getSessionId();
            }
            end = sessionId + "/" + end;
        }
        return realEndpoint +
                "/" + api.getConfiguration().getDevId() +
                "/" + generateSignature(endpoint) +
                "/" + end +
                ((queryParams.length > 0) ? "/" + String.join("/", queryParams) : "");
    }

    private String generateSignature(String endpoint) {
        String templateSignature = api.getConfiguration().getDevId() + endpoint + api.getConfiguration().getAuthKey() + getTimestamp();
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
