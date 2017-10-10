package com.github.stachu540.hirezapi.enums.url;

public interface BasePlatform {
    String getUrl();
    String getPlatform();
    default String getGame() {
        return getClass().getSimpleName();
    }
}
