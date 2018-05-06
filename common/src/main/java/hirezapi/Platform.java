package hirezapi;

import okhttp3.HttpUrl;

public interface Platform {
    String getGame();

    String getPlatform();

    HttpUrl getBaseUrl();
}
