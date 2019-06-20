package hirez.realm.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.adapters.DateTimeFormat;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PlayerMatchHistory implements ReturnedMessage {
    private final long id;
    private final List<Match> matches;
    private final String name;
    @JsonProperty("ret_msg")
    private final String returnedMessage;

    @Data
    public static class Match {
        private final int assists;
        private final long classId;
        private final String className;
        private final int creeps;
        private final int damage;
        private final int damageDoneInHand;
        private final int damageMitigated;
        private final int damageTaken;
        private final int deaths;
        private final int gold;
        private final int healingBot;
        private final int healingPlayer;
        private final int healingPlayerSelf;
        private final int killingSpreeMax;
        private final int kills;
        private final String mapGame;
        @DateTimeFormat("yyyy-MM-dd'T'hh:mm:ss.SSS")
        private final Date matchDatetime;
        private final long matchDurationSecs;
        private final long matchId;
        private final int matchQueueId;
        private final String matchQueueName;
        private final int placement;
        private final long playerId;
        private final String region;
        private final long teamId;
        private final long timeInMatchMinutes;
        private final long timeInMatchSecs;
        private final int wardsMinesPlaced;

    }
}
