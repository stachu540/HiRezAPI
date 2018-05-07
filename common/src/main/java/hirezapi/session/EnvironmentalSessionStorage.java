package hirezapi.session;

import hirezapi.Platform;
import hirezapi.json.SessionCreation;

import java.util.LinkedHashMap;
import java.util.Map;

public class EnvironmentalSessionStorage implements SessionStorage {
  private final Map<Platform, String> session = new LinkedHashMap<>();

  @Override
  public String set(Platform platform, SessionCreation session) {
    return this.session.put(platform, session.getSessionId());
  }

  @Override
  public String get(Platform platform) {
    return this.session.get(platform);
  }

  @Override
  public boolean remove(Platform platform) {
    return this.session.remove(platform) != null;
  }

  @Override
  public boolean contains(Platform platform) {
    return this.session.containsKey(platform);
  }
}
