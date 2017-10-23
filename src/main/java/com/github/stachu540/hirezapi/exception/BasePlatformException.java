package com.github.stachu540.hirezapi.exception;

import com.github.stachu540.hirezapi.enums.url.BasePlatform;

public class BasePlatformException extends RuntimeException {
  public BasePlatformException(BasePlatform platform) {
    super("Unknown base platform: " + platform.getGame());
  }
}
