package com.github.stachu540.hirezapi.enums.url;

import lombok.Getter;

@Getter
public enum EPaladins implements BasePlatform {
  /** Paladins PC API. */
  PC("http://api.paladins.com/paladinsapi.svc"),
  /** Paladins XBOX One API. */
  XBOX("http://api.xbox.paladins.com/paladinsapi.svc"),
  /** Paladins PlayStation 4 API. */
  PS4("http://api.ps4.paladins.com/paladinsapi.svc");

  /** API Url. */
  private final String url;
  /** Platform name. */
  private final String platform;

  /**
   * End points for API.
   * @param url URL for API
   */
  EPaladins(String url) {
    this.platform = name();
    this.url = url;
  }
}
