package com.github.stachu540.hirezapi.api;

import com.github.stachu540.hirezapi.enums.url.BasePlatform;

import java.util.HashMap;

public class HiRezSession extends HashMap<BasePlatform, String> {
  @Override
  public String put(BasePlatform platform, String sessionId) {
    if (containsKey(platform)) {
      return replace(platform, sessionId);
    } else {
      return super.put(platform, sessionId);
    }
  }
}
