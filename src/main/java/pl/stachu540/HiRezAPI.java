package pl.stachu540;

import pl.stachu540.hirezstudios.Paladins;
import pl.stachu540.hirezstudios.Smite;

import java.io.*;
import java.nio.file.Paths;
import java.util.Properties;


/**
 * @author <a href="damian@stachuofficial.pl">Damian Staszewski</a>
 * @since 1.8
 */
public class HiRezAPI {
    private static final File filesession = Paths.get(System.getProperty("java.io.tmpdir"), ".hirezsessions").toFile();

    /**
     * Session keys for specific base API url type
     */
    public static final Properties sessions = new Properties() {
        @Override
        public synchronized String setProperty(String key, String value) {
            put(key, value);
            try {
                HiRezAPI.store();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return value;
        }
    };

    /**
     * Developer ID (DevId)
     * @see HiRezAPI#HiRezAPI(String, String)
     */
    private final String devId;

    /**
     * Authorization Key (AuthKey)
     * @see HiRezAPI#HiRezAPI(String, String)
     */
    private final String authKey;

    /**
     * <p>Initialize Hi-Rez API. All stuff will delivered by Hi-Rez employer via E-Mail.</p>
     * <p>Please fill <a href="https://fs12.formsite.com/HiRez/form48/secure_index.html">this form first</a> to using script.</p>
     * <p>After acceptation your request, you can proceed to action.</p>
     * @param devId Developer ID (DevId)
     * @param authKey Authorization Key (AuthKey)
     */
    public HiRezAPI(String devId, String authKey) {
        this.devId = devId;
        this.authKey = authKey;
        try {
            initialize();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void store() throws IOException {
        Writer writer = new FileWriter(filesession);
        sessions.store(writer, "");
    }

    private void initialize() throws IOException {
        if (!filesession.exists()) {
            filesession.createNewFile();
        }
        Reader reader = new FileReader(filesession);
        sessions.load(reader);
    }

    /**
     * Smite API
     * @param platform Platform
     * @return {@link Smite}
     */
    public Smite smite(Smite.Platform platform) {
        return new Smite(platform, devId, authKey);
    }

    /**
     * Paladins API
     * @param platform Platform
     * @return {@link Paladins}
     */
    public Paladins paladins(Paladins.Platform platform) {
        return new Paladins(platform, devId, authKey);
    }

}
