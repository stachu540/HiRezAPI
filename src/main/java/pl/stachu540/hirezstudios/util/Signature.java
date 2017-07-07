package pl.stachu540.hirezstudios.util;

import pl.stachu540.util.MD5Hash;

/**
 * @author Damian Staszewski <damian@stachuofficial.pl>
 * @since 1.8
 */
public class Signature {
    private final String devId;
    private final String authKey;

    public Signature(String devId, String authKey) {
        this.devId = devId;
        this.authKey = authKey;
    }

    public MD5Hash generateSignature(String method, String timestamp) {
        return new MD5Hash(devId + method + authKey + timestamp);
    }
}
