package hirezapi;

import hirezapi.session.EnvironmentalSessionStorage;
import hirezapi.session.SessionStorage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractAPI<T extends HiRezApi> {
  private final Platform platform;
  private SessionStorage sessionStorage = new EnvironmentalSessionStorage();

  public abstract T init(String devId, String authKey);

  public abstract AbstractAPI<T> sessionStorage(SessionStorage sessionStorage);

  protected Configuration buildConfiguration(String devId, String authKey) {
    return Configuration.of(platform, devId, authKey);
  }
}
