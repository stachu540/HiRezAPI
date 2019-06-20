package hirez.api;

import hirez.api.object.Game;
import hirez.api.object.Platform;

public interface BaseEndpoint {
    Game getGame();

    Platform getPlatform();

    String getBaseUrl();
}
