import hirezapi.enums.Language;
import hirezapi.smite.SmiteAPI;
import hirezapi.smite.SmiteGame;
import hirezapi.smite.enums.Smite;
import hirezapi.smite.json.God;

import java.util.List;

public class SmiteTest {
    public static void main(String[] args) {
        String DEV_ID = "2128";
        String AUTH_KEY = "F23775B5CCDC43D2B4AA0CCCAD9AD2B1";

        SmiteGame smiteGame = SmiteAPI.of(Smite.PC)
                .init(DEV_ID, AUTH_KEY);
        List<God> gods = smiteGame.gameEndpoint().getGods(Language.English);
        gods.forEach(System.out::println);
    }
}
