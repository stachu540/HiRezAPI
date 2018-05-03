package hirezapi.paladins.enums;

import hirezapi.Platform;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Paladins implements Platform {
    PC("http://api.paladins.com/paladinsapi.svc"),
    XBOX("http://api.xbox.paladins.com/paladinsapi.svc"),
    PS4("http://api.ps4.paladins.com/paladinsapi.svc");

    private final String game = getClass().getSimpleName();
    private final String platform = name();
    private final String baseUrl;
}