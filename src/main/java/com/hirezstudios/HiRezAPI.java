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
//
//    public SmiteAPI smite(String platform) {
//        return new SmiteAPI(platform, getAuthorizations());
//    }

    public Map<String, String> getAuthorizations() {
       Map<String, String> auths = new HashMap<String, String>();
       auths.put("devId", devId);
       auths.put("authKey", authKey);
       return auths;
    }
//
//    public PaladinsAPI paladins(String platform) {
//
//    }
}
