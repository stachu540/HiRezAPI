package hirezapi.smite.enums;

import hirezapi.Platform;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import okhttp3.HttpUrl;

@Getter
@RequiredArgsConstructor
public enum Smite implements Platform {
    PC("http://api.smitegame.com/smiteapi.svc"),
    XBOX("http://api.xbox.smitegame.com/smiteapi.svcc"),
    PS4("http://api.ps4.smitegame.com/smiteapi.svc");

    private final String game = getClass().getSimpleName();
    private final String platform = name();
    private final HttpUrl baseUrl;

    Smite(String url) {
        this.baseUrl = HttpUrl.parse(url);
    }
}
