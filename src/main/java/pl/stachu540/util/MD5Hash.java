package pl.stachu540.util;

import java.security.MessageDigest;

/**
 * @author Damian Staszewski <damian@stachuofficial.pl>
 * @since 1.8
 */
public class MD5Hash {

    private final String data;
    private final StringBuilder hash = new StringBuilder();

    public MD5Hash(String data) {
        this.data = data;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes());

            byte byteData[] = md.digest();

            for (int i = 0; i < byteData.length; i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if (hex.length() == 1) hash.append("0");
                hash.append(hex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getText() { return data; }

    public String getHash() { return hash.toString(); }
}
