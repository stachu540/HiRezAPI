package com.github.stachu540.hirezapi.api;

import com.github.stachu540.hirezapi.enums.url.BasePlatform;
import com.github.stachu540.hirezapi.enums.url.EPaladins;
import com.github.stachu540.hirezapi.enums.url.ESmite;

import java.util.*;

public class HiRezSession extends HashMap<BasePlatform, String> {
    HiRezSession() {
        super();
        Map<String, String> envs = System.getenv();
        List<BasePlatform> platforms = new ArrayList<>();
        platforms.addAll(Arrays.asList(ESmite.values()));
        platforms.addAll(Arrays.asList(EPaladins.values()));
        platforms.forEach(platform -> {
            if (envs.containsKey(platform.getName()))
                put(platform, envs.get(platform.getName()));
        });
    }

    @Override
    public String put(BasePlatform platform, String sessionId) {
        if (!System.getenv().containsKey(platform.getName()) || !System.getenv().get(platform.getName()).equals(sessionId))
            System.getenv().put(platform.getName(), sessionId);

        remove(platform);
        return super.put(platform, sessionId);
    }
}
