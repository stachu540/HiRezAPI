package com.github.stachu540.hirezapi.enums.url;

import lombok.Getter;

@Getter
public enum ESmite implements BasePlatform {
    /**
     * Smite PC API
     */
    PC("http://api.smitegame.com/smiteapi.svc"),
    /**
     * Smite XBOX One API
     */
    XBOX("http://api.xbox.smitegame.com/smiteapi.svc"),
    /**
     * Smite PlayStation 4 API
     */
    PS4("http://api.ps4.smitegame.com/smiteapi.svc");

    /**
     * Platform name
     */
    private final String platform;
    /**
     * API URL
     */
    private final String url;

    /**
     * End points for API
     * @param url URL for API
     */
    ESmite(String url) {
        this.platform = name();
        this.url = url;
    }
}
