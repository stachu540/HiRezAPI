package com.github.stachu540.hirezapi.enums.url;

public interface BasePlatform {
    String getUrl();
    String getPlatform();
    default String getGame() {
        String name = getClass().getSimpleName();
        name = (name.startsWith("E")) ? name.substring(1) : name;
        return name;
    }

    default String getName() {
        return String.format("%S_%S", getGame(), getPlatform());
    }
}
