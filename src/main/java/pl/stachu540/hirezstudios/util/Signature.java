package pl.stachu540.hirezstudios.util;

import pl.stachu540.util.MD5Hash;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

/**
 * @author Damian Staszewski <damian@stachuofficial.pl>
 * @since 1.8
 */
public class Signature {
    private final String timestamp = generateTimestamp();
    private final String devId;
    private final String authKey;

    public Signature(String devId, String authKey) {
        this.devId = devId;
        this.authKey = authKey;
    }

    private String generateTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sdf.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));
        return sdf.format(new Date());
    }

    public String getTimestamp() { return timestamp; }

    public MD5Hash generateSignature(String method) {
        return new MD5Hash(devId + method + authKey + timestamp);
    }
}
