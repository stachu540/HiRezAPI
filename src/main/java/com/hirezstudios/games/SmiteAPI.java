package com.hirezstudios.games;

import org.json.JSONObject;
import pl.stachu540.API;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by stachu on 03.07.17.
 */
public class SmiteAPI extends API{
    private final String devId;
    private final String authKey;

    public enum Platform {
        XBOX, PS4, PC
    }

    public SmiteAPI(String devId, String authKey, Platform platform) {
        super("http://api." + ((!platform.name().equals("PC")) ? platform.name().toLowerCase() + "." : "") + "smitegame.com/smiteapi.svc");
        this.devId = devId;
        this.authKey = authKey;
    }

    public JSONObject ping() {
        return getData(requestType.GET, "/pingjson");
    }
}
