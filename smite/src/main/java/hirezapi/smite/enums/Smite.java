package hirezapi.smite.enums;

import hirezapi.Platform;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Smite implements Platform {
    PC("http://api.smitegame.com/smiteapi.svc"),
    XBOX("http://api.xbox.smitegame.com/smiteapi.svcc"),
    PS4("http://api.ps4.smitegame.com/smiteapi.svc");

    private final String game = getClass().getSimpleName();
    private final String platform = name();
    private final String baseUrl;
}
