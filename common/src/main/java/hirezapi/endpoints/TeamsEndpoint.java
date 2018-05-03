package hirezapi.endpoints;

import hirezapi.HiRezApi;
import hirezapi.json.SimplePlayer;
import hirezapi.json.SimpleTeam;
import hirezapi.json.Team;

import java.util.Arrays;
import java.util.List;

public class TeamsEndpoint extends AbstractEndpoint {

    public TeamsEndpoint(HiRezApi api) {
        super(api);
    }

    public Team getTeamById(long id) {
        return api.getRestClient().getForObject(buildUrl("getteamdetails", Long.toString(id)), Team[].class)[0];
    }

    public List<SimplePlayer> getTeamPlayers(long id) {
        return Arrays.asList(api.getRestClient().getForObject(buildUrl("getteamplayers", Long.toString(id)), SimplePlayer[].class));
    }

    public List<SimpleTeam> searchTeam(String team) {
        return Arrays.asList(api.getRestClient().getForObject(buildUrl("searchteams", team), SimpleTeam[].class));
    }
}
