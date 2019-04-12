package hirez.paladins;

import hirez.api.BaseEndpoint;
import hirez.api.object.Game;
import hirez.api.object.Platform;
import lombok.Getter;

@Getter
public enum PaladinsPlatform implements BaseEndpoint {
    PC("http://api.paladins.com/paladinsapi.svc", "8xmmtyh24dvk"),
    XBOX("http://api.xbox.paladins.com/paladinsapi.svc", "z44md5h2qg1f"),
    PS4("http://api.ps4.paladins.com/paladinsapi.svc", "m80484kp0zhn");

    private final String baseUrl;
    private final Platform platform;
    private final Game game = new Game("542zlqj9nwr6", "Paladins");

    PaladinsPlatform(String baseUrl, String platform) {
        this.baseUrl = baseUrl;
        this.platform = new Platform(platform, name());
    }
}
