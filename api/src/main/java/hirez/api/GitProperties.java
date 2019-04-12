package hirez.api;

import java.io.IOException;
import java.util.Properties;

public class GitProperties {
    private static final Properties PROPERTIES = new Properties();

    public static final String GIT_BRANCH = "git.branch";
    public static final String GIT_COMMIT_ID = "git.commit.id";
    public static final String GIT_COMMIT_ID_ABBREV = "git.commit.id.abbrev";
    public static final String GIT_COMMIT_ID_DESCRIBE = "git.commit.id.describe";
    public static final String APPLICATION_NAME = "application.name";
    public static final String APPLICATION_VERSION = "application.version";
    public static final String APPLICATION_URL = "application.url";
    public static final String APPLICATION_DESCRIPTION = "application.description";

    public static String get(String name) {
        return PROPERTIES.getProperty(name);
    }

    static {
        try {
            PROPERTIES.load(GitProperties.class.getClassLoader().getResourceAsStream("git.properties"));
        } catch (IOException ignore) {}
    }
}
