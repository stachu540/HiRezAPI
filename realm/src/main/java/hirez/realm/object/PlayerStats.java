package hirez.realm.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.adapters.DateTimeFormat;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class PlayerStats implements ReturnedMessage {
    private final AggregateStats aggregateStats;
    @DateTimeFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
    private final Date placementWindowEnd;
    @DateTimeFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
    private final Date placementWindowStart;
    private final long playerId;
    private final List<QueueClass> queueClassStats;
    @JsonProperty("ret_msg")
    private final String returnedMessage;


    @Data
    public static class QueueClass {
        private final long classId;
        private final String className;
        private final long matchQueueId;
        private final String matchQueueName;
        private final Stats stats;

        @Data
        public static class Stats {
            private final int assists;
            private final int averagePlacement;
            private final int damage;
            private final int damageDoneInHand;
            private final int damageMitigated;
            private final int damageTaken;
            private final int deaths;
            private final long durationSecs;
            private final int earnedTokens;
            private final int earnedXp;
            private final int gamesPlayed;
            private final int healingPlayer;
            private final double healingPlayerSelf;
            private final int killingSpreeMax;
            private final int killsBot;
            private final int killsPlayer;
            private final int losses;
            private final String placementList;
            private final List<Placement> placements;
            private final int wardsMinesPlaced;
            private final int wins;
        }
    }

    @Data
    public static class AggregateStats {
        private final int assists;
        private final int averagePlacement;
        private final int damage;
        private final int damageDoneInHand;
        private final int damageMitigated;
        private final int damageTaken;
        private final int deaths;
        private final long durationSecs;
        private final int earnedTokens;
        private final int earnedXp;
        private final int gamesPlayed;
        private final float healingPlayer;
        private final float healingPlayerSelf;
        private final int killingSpreeMax;
        private final int killsBot;
        private final int killsPlayer;
        private final int losses;
        private final String placementList;
        private final List<Placement> placements;
        private final int wardsMinesPlaced;
        private final int wins;

    }

    @Data
    public static class Placement {
        private final int occurrences;
        private final int position;
    }
}
