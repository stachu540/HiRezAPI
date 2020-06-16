package hirez.api.sessions;

import hirez.api.SessionStorage;
import hirez.api.object.CreateSession;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;

import java.io.*;

@RequiredArgsConstructor
public class FileSessionStorage implements SessionStorage {
    private final File file;

    public static SessionStorage create() {
        return create(new File("hirez-session.txt"));
    }

    public static SessionStorage create(File file) {
        return new FileSessionStorage(file);
    }

    @Override
    public String get() throws NullPointerException {
        if (isPresent()) {
            try {
                return readFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else
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
            try {
                writeFile(session.getSessionId());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    @Override
    public boolean isPresent() {
        return file.exists() && isEmpty();
    }

    private String readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String data = reader.readLine();
        reader.close();

        return data;
    }

    private void writeFile(String token) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(token);
        writer.close();
    }

    private boolean isEmpty() {
        try {
            return readFile().isEmpty();
        } catch (IOException e) {
            return false;
        }
    }
}
