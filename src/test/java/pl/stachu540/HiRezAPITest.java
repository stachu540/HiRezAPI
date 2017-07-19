package pl.stachu540;

import org.junit.jupiter.api.*;
import pl.stachu540.hirezstudios.HiRezGames;
import pl.stachu540.hirezstudios.Paladins;
import pl.stachu540.hirezstudios.Smite;

import java.util.Arrays;
import java.util.HashMap;

class HiRezAPITest {

    static HiRezAPI api;
    static HashMap<String, HiRezGames> games = new HashMap<String, HiRezGames>();;

    @BeforeAll
    static void prepare(){
        String dev_id = System.getenv("DEV_ID");
        String auth_key = System.getenv("AUTH_KEY");
        if (!dev_id.isEmpty() || !auth_key.isEmpty()) api = new HiRezAPI(dev_id, auth_key);
        else if (dev_id.isEmpty()) throw new Error("Developer ID is empty.");
        else if (auth_key.isEmpty()) throw new Error("Authorization Key empty.");
        Arrays.asList(Smite.Platform.values()).forEach(platform -> games.put("SMITE_" + platform.name(), api.smite(platform)));
        Arrays.asList(Paladins.Platform.values()).forEach(platform -> games.put("PALADINS_" + platform.name(), api.paladins(platform)));
    }

    @BeforeEach
    void setUp() {
    }


    @Test
    @DisplayName("Ping Test")
    void pingTesting() {
        games.forEach((s, api_data) -> {
            api_data.ping();
        });
    }

    @Test
    @DisplayName("Session Test")
    void sessionTesting() {
        games.forEach((s, api_data) -> {
            api_data.testSession();
        });
    }

    @Test
    @DisplayName("List Patches Test")
    void patchesTesting() {
        games.forEach((s, api_data) -> {
            api_data.getPatchInfo();
        });
    }

    @AfterEach
    void tearDown() {

    }
    @AfterAll
    static void release() {

    }

}