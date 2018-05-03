package hirezapi.endpoints;

import hirezapi.HiRezApi;
import hirezapi.json.DataUsage;
import hirezapi.json.Ping;
import hirezapi.json.SessionCreation;
import hirezapi.json.SessionTest;
import hirezapi.session.EnvironmentalSessionStorage;
import hirezapi.session.SessionCreationException;
import hirezapi.session.SessionStorage;
import lombok.Getter;
import lombok.Setter;

public class SessionEndpoint extends AbstractEndpoint {
    @Getter
    @Setter
    private SessionStorage sessionStorage;

    public SessionEndpoint(HiRezApi api, SessionStorage sessionStorage) {
        super(api);
        this.sessionStorage = sessionStorage;
    }

    public SessionEndpoint(HiRezApi api) {
        super(api);
        setSessionStorage(new EnvironmentalSessionStorage());
    }

    public Ping ping() {
        return new Ping(api.getRestClient().getForObject("/pingjson", String.class));
    }

    public SessionCreation create() {
        SessionCreation sessionCreation = api.getRestClient().getForObject(buildUrl("createsession"), SessionCreation.class);
        if (!sessionCreation.getReturnedMessage().equals("Approved")){
            throw new SessionCreationException(sessionCreation);
        } else {
            sessionStorage.set(api.getConfiguration().getPlatform(), sessionCreation);
        }

        return sessionCreation;
    }

    public SessionTest test() {
        return new SessionTest(api.getRestClient().getForObject(buildUrl("testsession"), String.class));
    }

    public DataUsage getDataUsage() {
        return api.getRestClient().getForObject(buildUrl("getdataused"), DataUsage[].class)[0];
    }
}
