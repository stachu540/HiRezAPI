package com.hirezstudios.games;

import org.json.JSONObject;
import pl.stachu540.API;

/**
 * Created by stachu on 04.07.17.
 */
public class PaladinsAPI extends API {
    private final String devId;
    private final String authKey;

    public enum Platform {
        XBOX, PS4, PC
    }

    public PaladinsAPI(String devId, String authKey, Platform platform) {
        super("http://api." + ((!platform.name().equals("PC")) ? platform.name().toLowerCase() + "." : "") + "paladins.com/paladinsapi.svc");
        this.devId = devId;
        this.authKey = authKey;
    }

    public JSONObject ping() {
        return getData(requestType.GET, "/pingjson");
    }
}
