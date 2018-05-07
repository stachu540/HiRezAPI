package hirezapi;

import hirezapi.endpoints.GameEndpoint;
import hirezapi.endpoints.MatchesEndpoint;
import hirezapi.endpoints.SessionEndpoint;
import hirezapi.endpoints.TeamsEndpoint;
import hirezapi.endpoints.UserEndpoint;
import hirezapi.rest.RestController;
import hirezapi.session.SessionStorage;
import lombok.AccessLevel;
import lombok.Getter;

@Getter
public abstract class HiRezApi<G extends GameEndpoint> {
  protected final Configuration configuration;
  protected final RestController restController;
  @Getter(AccessLevel.NONE)
  private final SessionEndpoint sessionEndpoint;

  protected HiRezApi(Configuration configuration, SessionStorage sessionStorage) {
    this.configuration = configuration;
    this.restController = new RestController(configuration.getPlatform());
    this.sessionEndpoint = new SessionEndpoint(this, sessionStorage);
  }

  public UserEndpoint userEndpoint() {
    return new UserEndpoint(this);
  }

  public SessionEndpoint sessionEndpoint() {
    return sessionEndpoint;
  }

  public MatchesEndpoint matchesEndpoint() {
    return new MatchesEndpoint(this);
  }

  public TeamsEndpoint teamsEndpoint() {
    return new TeamsEndpoint(this);
  }

  public abstract G gameEndpoint();
}
