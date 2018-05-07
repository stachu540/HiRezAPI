package hirezapi.session;

import hirezapi.Platform;
import hirezapi.json.SessionCreation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileSessionStorage implements SessionStorage {
  private final Properties properties;

  /**
   * A {@link SessionStorage session storage} saving into file.
   * @see Properties
   * @param file a file
   */
  public FileSessionStorage(File file) {
    this.properties = new Properties();
    try {
      this.properties.load(new FileInputStream(file));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String set(Platform platform, SessionCreation session) {
    return (String) properties.setProperty(retrieveName(platform), session.getSessionId());
  }

  @Override
  public String get(Platform platform) {
    return properties.getProperty(retrieveName(platform));
  }

  private String retrieveName(Platform platform) {
    return String.format("%S_%S", platform.getGame(), platform.getPlatform());
  }

  @Override
  public boolean remove(Platform platform) {
    return properties.remove(retrieveName(platform)) != null;
  }

  @Override
  public boolean contains(Platform platform) {
    return properties.contains(retrieveName(platform));
  }
}
