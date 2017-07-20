package pl.stachuofficial;

import org.junit.jupiter.api.*;
import pl.stachuofficial.hirezstudios.Paladins;
import pl.stachuofficial.hirezstudios.Smite;
import pl.stachuofficial.util.StringData;

@DisplayName("Testing API")
class HiRezAPITest {

    static class HiRezTest extends HiRezAPI{

        HiRezTest(String devId, String authKey) {
            super(devId, authKey);
        }

        StringData ping(Smite.Platform platform) {
            return new StringData(smite(platform).ping());
        }

        StringData test(Smite.Platform platform) {
            return new StringData(smite(platform).testSession());
        }

        StringData patch(Smite.Platform platform) {
            return smite(platform).getPatchInfo();
        }

        StringData ping(Paladins.Platform platform) {
            return new StringData(paladins(platform).ping());
        }

        StringData test(Paladins.Platform platform) {
            return new StringData(paladins(platform).testSession());
        }

        StringData patch(Paladins.Platform platform) {
            return paladins(platform).getPatchInfo();
        }
    }

    private static HiRezTest apitest;

    @BeforeAll
    static void setUp() {
        String dev_id = System.getenv("DEV_ID");
        String auth_key = System.getenv("AUTH_KEY");
        if (dev_id.isEmpty()) throw new Error("Developer ID is empty.");
        else if (auth_key.isEmpty()) throw new Error("Authorization Key empty.");
        apitest = new HiRezTest(dev_id, auth_key);
    }

    void testData(boolean condition, String title) {
        if (condition) System.out.println(title+": OK");
        else System.err.println(title+": ERROR");
        Assertions.assertTrue(condition, title);
    }

    @Test
    @DisplayName("Smite PC")
    void smitePc() {
        Assertions.assertAll(
                () -> {
                    String data = apitest.ping(Smite.Platform.PC).toString();
                    testData(data.contains("Ping successful."), "Smite PC - Ping API");
                },
                () -> {
                    String data = apitest.test(Smite.Platform.PC).toString();
                    testData(data.contains("This was a successful test with the following parameters added:"),
                            "Smite PC - Session Test");
                },
                () -> {
                    StringData data = apitest.patch(Smite.Platform.PC);
                    testData(data.toJsonObject().get("ret_msg").equals(null) && !data.toJsonObject().getString("version_string").isEmpty(),
                            "Smite PC - Patch Notes Test");
                });
    }
    @Test
    @DisplayName("Smite XBOX")
    void smiteXbox() {
        Assertions.assertAll(
                () -> {
                    String data = apitest.ping(Smite.Platform.XBOX).toString();
                    testData(data.contains("Ping successful."), "Smite XBOX - Ping API");
                },
                () -> {
                    String data = apitest.test(Smite.Platform.XBOX).toString();
                    testData(data.contains("This was a successful test with the following parameters added:"),
                            "Smite XBOX - Session Test");
                },
                () -> {
                    StringData data = apitest.patch(Smite.Platform.XBOX);
                    testData(data.toJsonObject().get("ret_msg").equals(null) && !data.toJsonObject().getString("version_string").isEmpty(),
                            "Smite XBOX - Patch Notes Test");
                });
    }
    @Test
    @DisplayName("Smite PS4")
    void smitePs4() {
        Assertions.assertAll(
                () -> {
                    String data = apitest.ping(Smite.Platform.PS4).toString();
                    testData(data.contains("Ping successful."), "Smite PS4 - Ping API");
                },
                () -> {
                    String data = apitest.test(Smite.Platform.PS4).toString();
                    testData(data.contains("This was a successful test with the following parameters added:"),
                            "Smite PS4 - Session Test");
                },
                () -> {
                    StringData data = apitest.patch(Smite.Platform.PS4);
                    testData(data.toJsonObject().get("ret_msg").equals(null) && !data.toJsonObject().getString("version_string").isEmpty(),
                            "Smite PS4 - Patch Notes Test");
                });
    }

    @Test
    @DisplayName("Paladins PC")
    void paladinsPc() {
        Assertions.assertAll(
                () -> {
                    String data = apitest.ping(Paladins.Platform.PC).toString();
                    testData(data.contains("Ping successful."), "Paladins PC - Ping API");
                },
                () -> {
                    String data = apitest.test(Paladins.Platform.PC).toString();
                    testData(data.contains("This was a successful test with the following parameters added:"),
                            "Paladins PC - Session Test");
                },
                () -> {
                    StringData data = apitest.patch(Paladins.Platform.PC);
                    testData(data.toJsonObject().get("ret_msg").equals(null) && !data.toJsonObject().getString("version_string").isEmpty(),
                            "Paladins PC - Patch Notes Test");
                });
    }
    @Test
    @DisplayName("Paladins XBOX")
    void paladinsXbox() {
        Assertions.assertAll(
                () -> {
                    String data = apitest.ping(Paladins.Platform.XBOX).toString();
                    testData(data.contains("Ping successful."), "Paladins XBOX - Ping API");
                },
                () -> {
                    String data = apitest.test(Paladins.Platform.XBOX).toString();
                    testData(data.contains("This was a successful test with the following parameters added:"),
                            "Paladins XBOX - Session Test");
                },
                () -> {
                    StringData data = apitest.patch(Paladins.Platform.XBOX);
                    testData(data.toJsonObject().get("ret_msg").equals(null) && !data.toJsonObject().getString("version_string").isEmpty(),
                            "Paladins XBOX - Patch Notes Test");
                });
    }
    @Test
    @DisplayName("Paladins PS4")
    void paladinsPs4() {
        Assertions.assertAll(
                () -> {
                    String data = apitest.ping(Paladins.Platform.PS4).toString();
                    testData(data.contains("Ping successful."), "Paladins PS4 - Ping API");
                },
                () -> {
                    String data = apitest.test(Paladins.Platform.PS4).toString();
                    testData(data.contains("This was a successful test with the following parameters added:"),
                            "Paladins PS4 - Session Test");
                },
                () -> {
                    StringData data = apitest.patch(Paladins.Platform.PS4);
                    testData(data.toJsonObject().get("ret_msg").equals(null) && !data.toJsonObject().getString("version_string").isEmpty(),
                            "Paladins PS4 - Patch Notes Test");
                });
    }

    @AfterEach
    void tearDown() {

    }
}