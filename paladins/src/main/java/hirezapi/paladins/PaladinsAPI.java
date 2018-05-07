package hirezapi.paladins;

import hirezapi.AbstractAPI;
import hirezapi.paladins.enums.Paladins;
import hirezapi.session.SessionStorage;

import java.util.Objects;

public class PaladinsAPI extends AbstractAPI {

  private PaladinsAPI(Paladins platform) {
    super(platform);
  }

  public static PaladinsAPI of(Paladins platform) {
    return new PaladinsAPI(platform);
  }

  /**
   * Initialize API.
   * @param devId Developer ID
   * @param authKey Authorization Key
   * @return Paladins Game API endpoint
   */
  public PaladinsGame init(String devId, String authKey) {
    return new PaladinsGame(buildConfiguration(
          Objects.requireNonNull(devId, "DEV_ID"),
          Objects.requireNonNull(authKey, "AUTH_KEY")),
          getSessionStorage());
  }

  @Override
  public PaladinsAPI sessionStorage(SessionStorage sessionStorage) {
    setSessionStorage(sessionStorage);
    return this;
  }
}
