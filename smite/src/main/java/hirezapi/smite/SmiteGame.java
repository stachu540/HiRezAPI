package hirezapi.smite;

import hirezapi.Configuration;
import hirezapi.HiRezApi;
import hirezapi.session.SessionStorage;
import hirezapi.smite.endpoint.SmiteEndpoint;

public class SmiteGame extends HiRezApi<SmiteEndpoint> {
  SmiteGame(Configuration config, SessionStorage sessionStorage) {
    super(config, sessionStorage);
  }

  @Override
  public SmiteEndpoint gameEndpoint() {
    return new SmiteEndpoint(this);
  }
}
