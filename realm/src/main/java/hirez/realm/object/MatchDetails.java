package hirez.realm.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.adapters.DateTimeFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MatchDetails {
    private final long durationSecs;
    @DateTimeFormat("yyyy-MM-dd'T'hh:mm:ss.SSS")
    private final Date matchDatetime;
    private final long matchId;
    private final int matchQueueId;
    private final String matchQueueName;
    private final String region;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    private final List<Team> teams;

    @Data
    public static class Team {

        private final long id;
        private final int placement;
        private final List<Player> players;

        @Data
        public static class Player {

            private final int assists;
            private final long classId;
            private final String className;
            private final int damageDoneInHand;
            private final int damageMitigated;
            private final int damagePlayer;
            private final int damageTaken;
            private final int deaths;
            private final int droppedOutFlag;
            private final int durationSecs;
            private final int earnedTokens;
            private final int earnedXp;
            private final int healingPlayer;
            private final int healingPlayerSelf;
            private final long id;
            private final int killingSpreeMax;
            private final int killsBot;
            private final int killsPlayer;
            private final int level;
            private final int minesWardsPlaced;
            private final String name;
        }
    }
}
