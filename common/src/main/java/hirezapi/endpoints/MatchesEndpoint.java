package hirezapi.endpoints;

import hirezapi.HiRezApi;
import hirezapi.enums.Queue;
import hirezapi.json.DemoDetails;
import hirezapi.json.LeagueSeason;
import hirezapi.json.ProLeagueMatch;

import java.util.Arrays;
import java.util.List;


public class MatchesEndpoint extends AbstractEndpoint {

    public MatchesEndpoint(HiRezApi api) {
        super(api);
    }

//    public DemoDetails getDemoDetails(Match match) {
//        return getDemoDetails(match.getId());
//    }
    public DemoDetails getDemoDetails(long matchId) {
        return api.getRestClient().getForObject(buildUrl("getdemodetails", Long.toString(matchId)), DemoDetails[].class)[0];
    }

    public List<ProLeagueMatch> getEsportsProLeagueDetails() {
        return Arrays.asList(api.getRestClient().getForObject(buildUrl("getesportsproleaguedetails"), ProLeagueMatch[].class));
    }

    public List<LeagueSeason> getLeagueSeasons(Queue queue) {
        return Arrays.asList(api.getRestClient().getForObject(buildUrl("getleagueseasons", Integer.toString(queue.getId())), LeagueSeason[].class));
    }
}
