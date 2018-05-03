import hirezapi.smite.SmiteAPI;
import hirezapi.smite.SmiteGame;
import hirezapi.smite.enums.Smite;

public class SmiteTest {
    public static void main(String[] args) {
        SmiteGame smiteGame = SmiteAPI.of(Smite.PC)
                .init(System.getenv("DEV_ID"), System.getenv("AUTH_KEY"));
        System.out.println(smiteGame.gameEndpoint().serverStatus().getFeeds());
    }
}
