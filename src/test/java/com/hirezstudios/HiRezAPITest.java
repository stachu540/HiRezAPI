package com.hirezstudios;

import com.hirezstudios.games.PaladinsAPI;
import com.hirezstudios.games.SmiteAPI;
import org.json.JSONObject;


/**
 * Created by stachu on 04.07.17.
 */
public class HiRezAPITest {

    public void RunTest() {
        System.out.println("Prepare for pinging API calls");
        SmiteCheck();
        PaladinsCheck();
    }

    private static void SmiteCheck() {
        System.out.println("[ Smite ]");
        for(SmiteAPI.Platform platform : SmiteAPI.Platform.values()) {
            SmiteAPI smite = new SmiteAPI("", "", platform);
            JSONObject data = smite.ping();
            String state = (data.getBoolean("_success")) ? "OK" : data.getString("_exceptionMessage");
            System.out.println(platform.name() + ": " + state);
            if (data.getBoolean("_success")) System.out.println(data.getString("_content"));
            else System.exit(1);
        }
    }

    private static void PaladinsCheck() {
        System.out.println("[ Paladins ]");
        for(PaladinsAPI.Platform platform : PaladinsAPI.Platform.values()) {
            PaladinsAPI paladins = new PaladinsAPI("", "", platform);
            JSONObject data = paladins.ping();
            String state = (data.getBoolean("_success")) ? "OK" : data.getString("_exceptionMessage");
            System.out.println(platform.name() + ": " + state);
            if (data.getBoolean("_success")) System.out.println(data.getString("_content"));
            else System.exit(1);
        }
    }
}
