package hirezapi.paladins;

import hirezapi.Configuration;
import hirezapi.HiRezApi;
import hirezapi.paladins.endpoint.PaladinsEndpoint;
import hirezapi.session.SessionStorage;

public class PaladinsGame extends HiRezApi<PaladinsEndpoint> {
  PaladinsGame(Configuration configuration, SessionStorage sessionStorage) {
    super(configuration, sessionStorage);
  }

  @Override
  public PaladinsEndpoint gameEndpoint() {
    return new PaladinsEndpoint(this);
  }
}
