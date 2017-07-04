package com.hirezstudios;

import com.hirezstudios.games.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by stachu on 03.07.17.
 */
public class HiRezAPI {
    private final String devId;
    private final String authKey;

    public HiRezAPI(String devId, String authKey) {
        this.devId = devId;
        this.authKey = authKey;
    }

    public HiRezAPI(int devId, String authKey) {
        this.devId = Integer.toString(devId);
        this.authKey = authKey;
    }

    public SmiteAPI smite(SmiteAPI.Platform platform) {
        return new SmiteAPI(devId, authKey, platform);
    }

    public Map<String, String> getAuthorizations() {
       Map<String, String> auths = new HashMap<String, String>();
       auths.put("devId", devId);
       auths.put("authKey", authKey);
       return auths;
    }

    public PaladinsAPI paladins(PaladinsAPI.Platform platform) {
        return new PaladinsAPI(devId, authKey, platform);
    }
}
