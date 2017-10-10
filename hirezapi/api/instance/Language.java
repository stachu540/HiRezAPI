package pl.stachuofficial.hirezapi.api.instance;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="damian@stachuofficial.pl">Damian Staszewski</a>
 * @since 1.8
 */
public enum Language {
    English(1),
    German(2),
    French(3),
    Spanish(7),
    Latin_Spanish(9),
    Portuguese(10),
    Russian(11),
    Polish(12),
    Turkish(13);

    private int id;

    private static Map<Integer, Language> map = new HashMap<Integer, Language>();

    static {
        for(Language queue : Language.values()) {
            map.put(queue.id, queue);
        }
    }

    Language(final int id) {
        this.id = id;
    }

    public int getId() { return id; }

    public static Language valueOf(int id) {
        return map.get(id);
    }
}
