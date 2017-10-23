package com.github.stachu540.hirezapi.enums.url;

public interface BasePlatform {

  /**
   * Getting url.
   * @return URL
   */
  String getUrl();

  /**
   * Getting platform.
   * @return Platform name
   */
  String getPlatform();

  /**
   * Getting game name.
   * @return Name of game
   */
  default String getGame() {
    String name = getClass().getSimpleName();
    name = (name.startsWith("E")) ? name.substring(1) : name;
    return name;
  }

  default String getName() {
    return String.format("%S_%S", getGame(), getPlatform());
  }
}
