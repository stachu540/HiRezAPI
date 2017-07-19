package pl.stachu540;

import org.junit.jupiter.api.*;
import pl.stachu540.hirezstudios.HiRezGames;
import pl.stachu540.hirezstudios.Paladins;
import pl.stachu540.hirezstudios.Smite;
import pl.stachu540.util.StringData;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Hi-Rez API Test")
class HiRezAPITest {

    static HiRezAPI api;
    static HashMap<String, HiRezGames> games = new HashMap<String, HiRezGames>();;

    @BeforeAll
    static void prepare(){
        String dev_id = System.getenv("DEV_ID");
        String auth_key = System.getenv("AUTH_KEY");
        if (!dev_id.isEmpty() || !auth_key.isEmpty()) api = new HiRezAPI(dev_id, auth_key);
        else if (dev_id.isEmpty()) fail(new Error("Developer ID is empty."));
        else if (auth_key.isEmpty()) fail("Authorization Key empty.");
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
            String data = api_data.ping();
            System.out.println(data);
            assertTrue(data.indexOf("Ping successful.") != -1);
        });
    }

    @Test
    @DisplayName("Session Test")
    void sessionTesting() {
        games.forEach((s, api_data) -> {
            api_data.createSession();
            StringData data = api_data.testSession();
            System.out.println(data.toJsonArray());
        });
    }

    @Test
    @DisplayName("List Patches Test")
    void patchesTesting() {
        games.forEach((s, api_data) -> {
            StringData data = api_data.getPatchInfo();
            System.out.println(data.toJsonArray());
        });
    }

    @AfterEach
    void tearDown() {

    }
    @AfterAll
    static void release() {

    }

}