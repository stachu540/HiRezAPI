package hirezapi.endpoints.statuspage;

import hirezapi.HiRezApi;
import hirezapi.endpoints.AbstractEndpoint;
import hirezapi.endpoints.statuspage.Feeds;
import hirezapi.json.ApiServerStatus;

public class ServerStatus extends AbstractEndpoint {

    public ServerStatus(HiRezApi api) {
        super(api);
    }

    public ApiServerStatus getStatus() {
        return api.getRestClient().getForObject(buildUrl("gethirezserverstatus"), ApiServerStatus[].class)[0];
    }

    public Feeds getFeeds(boolean allPlatforms) {
        return new Feeds(api.getConfiguration(), allPlatforms);
    }

    public Feeds getFeeds() {
        return getFeeds(false);
    }
}
