package hirez.api;

import hirez.api.object.CreateSession;
import hirez.api.sessions.CachedSessionStorage;

import java.util.function.Consumer;

public interface SessionStorage {
    SessionStorage DEFAULT = CachedSessionStorage.create();

    String get() throws NullPointerException;

    void get(Consumer<String> session);

    void set(CreateSession session);

    boolean isPresent();
}
