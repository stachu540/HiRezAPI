package hirez.api.sessions;

import hirez.api.SessionStorage;
import hirez.api.object.CreateSession;
import java.util.function.Consumer;

public class CachedSessionStorage implements SessionStorage {
    private String session;

    public static SessionStorage create() {
        return new CachedSessionStorage();
    }

    @Override
    public String get() throws NullPointerException {
        if (isPresent())
            return session;
        else
            throw new NullPointerException("Please register session first!");
    }

    @Override
    public void get(Consumer<String> session) {
        try {
            session.accept(get());
        } catch (Throwable ignore) {
        }
    }

    @Override
    public void set(CreateSession session) {
        if (session.getReturnedMessage().equals("Approved")) {
            this.session = session.getSessionId();
        } else throw new IllegalStateException(session.getReturnedMessage());
    }

    @Override
    public boolean isPresent() {
        return session != null;
    }
}
