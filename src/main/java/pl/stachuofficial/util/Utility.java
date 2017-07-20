package pl.stachuofficial.util;

import pl.stachuofficial.HiRezAPI;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

/**
 * @author <a href="damian@stachuofficial.pl">Damian Staszewski</a>
 * @since 1.8
 */
public class Utility {
    /**
     * Generating timestamp
     * @return generated timestamp formatted into UTC time <code>yyyyMMddHHmmss</code>
     */
    public static String generateTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sdf.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));
        return sdf.format(new Date());
    }

    /**
     * Generating Signature
     * @param devId Developer ID (DevId)
     * @param authKey Authorization Key (AuthKey)
     * @param method Method name
     * @return MD5 generated signature
     */
    public static String generateSignature(String devId, String authKey, String method) {
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

    public static String generateUrl(String platform, String method, String devId, String authKey) {
        return method + "Json/" + devId + "/" +
                generateSignature(devId, authKey, method) + "/" +
                HiRezAPI.sessions.getProperty(platform) + "/" +
                generateTimestamp();

    }
}
