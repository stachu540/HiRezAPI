package hirezapi;

import hirezapi.endpoints.*;
import hirezapi.session.SessionStorage;
import lombok.Getter;
import org.springframework.web.client.RestTemplate;

@Getter
public abstract class HiRezApi<G extends GameEndpoint> {
    protected final Configuration configuration;
    protected final RestTemplate restClient;
    private final SessionEndpoint sessionEndpoint;

    protected HiRezApi(Configuration configuration, RestTemplate restClient, SessionStorage sessionStorage) {
        this.configuration = configuration;
        this.restClient = restClient;
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
