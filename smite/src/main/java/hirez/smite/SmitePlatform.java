package hirez.smite;

import hirez.api.BaseEndpoint;
import hirez.api.object.Game;
import hirez.api.object.Platform;
import lombok.Getter;

@Getter
public enum SmitePlatform implements BaseEndpoint {
    PC("http://api.smitegame.com/smiteapi.svc", "23d1x2hb4kyq"),
    XBOX("http://api.xbox.smitegame.com/smiteapi.svc", "7q3rm3krkkt6"),
    PS4("http://api.ps4.smitegame.com/smiteapi.svc", "glnkmmppldgp");

    private final String baseUrl;
    private final Platform platform;
    private final Game game = new Game("542zlqj9nwr6", "Smite");

    SmitePlatform(String baseUrl, String platform) {
        this.baseUrl = baseUrl;
        this.platform = new Platform(platform, name());
    }
}
