package hirez.realm.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.adapters.DateTimeFormat;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@SuppressWarnings("unused")
public class PlayerStats implements ReturnedMessage {
    private final AggregateStats aggregateStats;
    @DateTimeFormat("yyyy-MM-dd'T'hh:mm:ss.SSS")
    private final Date placementWindowEnd;
    @DateTimeFormat("yyyy-MM-dd'T'hh:mm:ss")
    private final Date placementWindowStart;
    private final long playerId;
    private final List<QueueClass> queueClassStats;
    @JsonProperty("ret_msg")
    private final String returnedMessage;


    @Data
    @SuppressWarnings("unused")
    public static class QueueClass {
        private final long classId;
        private final String className;
        private final long matchQueueId;
        private final String matchQueueName;
        private final Stats stats;

        @Data
        @SuppressWarnings("unused")
        public static class Stats {
            private final int assists;
            @JsonProperty("average_placement")
            private final int averagePlacement;
            private final int damage;
            @JsonProperty("damage_done_in_hand")
            private final int damageDoneInHand;
            @JsonProperty("damage_mitigated")
            private final int damageMitigated;
            @JsonProperty("damage_taken")
            private final int damageTaken;
            private final int deaths;
            @JsonProperty("duration_secs")
            private final long durationSecs;
            @JsonProperty("earned_tokens")
            private final int earnedTokens;
            @JsonProperty("earned_xp")
            private final int earnedXp;
            @JsonProperty("games_played")
            private final int gamesPlayed;
            @JsonProperty("healing_player")
            private final int healingPlayer;
            @JsonProperty("healing_player_self")
            private final double healingPlayerSelf;
            @JsonProperty("killing_spree_max")
            private final int killingSpreeMax;
            @JsonProperty("kills_bot")
            private final int killsBot;
            @JsonProperty("kills_player")
            private final int killsPlayer;
            private final int losses;
            @JsonProperty("placement_list")
            private final Map<Integer, Integer> placementList;
            private final List<Placement> placements;
            @JsonProperty("wards_mines_placed")
            private final int wardsMinesPlaced;
            private final int wins;
        }
    }

    @Data
    @SuppressWarnings("unused")
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
        private final Map<Integer, Integer> placementList;
        private final List<Placement> placements;
        private final int wardsMinesPlaced;
        private final int wins;

    }

    @Data
    @SuppressWarnings("unused")
    public static class Placement {
        private final int occurrences;
        private final int position;
    }
}
