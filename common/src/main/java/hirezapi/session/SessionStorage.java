package hirezapi.session;

import hirezapi.Platform;
import hirezapi.json.SessionCreation;

public interface SessionStorage {
    String set(Platform platform, SessionCreation session);

    String get(Platform platform);

    boolean remove(Platform platform);

    boolean contains(Platform platform);
}
