package hirez.smite.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.MergedAccount;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Player {
    @JsonProperty("ActivePlayerId")
    private final long activePlayerId;
    @JsonProperty("Avatar_URL")
    private final String avatarURL;
    @JsonProperty("Created_Datetime")
    private final String createdDatetime;
    @JsonProperty("HoursPlayed")
    private final long hoursPlayed;
    @JsonProperty("hz_gamer_tag")
    private final String hzGamerTag;
    @JsonProperty("hz_player_name")
    private final String hzPlayerName;
    @JsonProperty("Id")
    private final long id;
    @JsonProperty("Last_Login_Datetime")
    private final String lastLoginDatetime;
    @JsonProperty("Leaves")
    private final long leaves;
    @JsonProperty("Level")
    private final long level;
    @JsonProperty("Losses")
    private final long losses;
    @JsonProperty("MasteryLevel")
    private final long masteryLevel;
    @JsonProperty("MergedPlayers")
    private final MergedAccount[] mergedPlayers;
    @JsonProperty("Name")
    private final String name;
    @JsonProperty("Personal_Status_Message")
    private final String personalStatusMessage;
    @JsonProperty("Rank_Stat_Conquest")
    private final double rankStatConquest;
    @JsonProperty("Rank_Stat_Duel")
    private final long rankStatDuel;
    @JsonProperty("Rank_Stat_Joust")
    private final long rankStatJoust;
    @JsonProperty("RankedConquest")
    private final RankItem rankedConquest;
    @JsonProperty("RankedConquestController")
    private final RankItem rankedConquestController;
    @JsonProperty("RankedDuel")
    private final RankItem rankedDuel;
    @JsonProperty("RankedDuelController")
    private final RankItem rankedDuelController;
    @JsonProperty("RankedJoust")
    private final RankItem rankedJoust;
    @JsonProperty("RankedJoustController")
    private final RankItem rankedJoustController;
    @JsonProperty("Region")
    private final String region;
    @JsonProperty("ret_msg")
    private final Object retMsg;
    @JsonProperty("TeamId")
    private final long teamId;
    @JsonProperty("Team_Name")
    private final String teamName;
    @JsonProperty("Tier_Conquest")
    private final long tierConquest;
    @JsonProperty("Tier_Duel")
    private final long tierDuel;
    @JsonProperty("Tier_Joust")
    private final long tierJoust;
    @JsonProperty("Total_Achievements")
    private final long totalAchievements;
    @JsonProperty("Total_Worshippers")
    private final long totalWorshippers;
    @JsonProperty("Wins")
    private final long wins;

    @Data
    @SuppressWarnings("unused")
    public static class RankItem {
        @JsonProperty("Leaves")
        private final long leaves;
        @JsonProperty("Losses")
        private final long losses;
        @JsonProperty("Name")
        private final String name;
        @JsonProperty("player_id")
        private final Object playerId;
        @JsonProperty("Points")
        private final long points;
        @JsonProperty("PrevRank")
        private final long prevRank;
        @JsonProperty("Rank")
        private final long rank;
        @JsonProperty("ret_msg")
        private final Object retMsg;
        @JsonProperty("Season")
        private final long season;
        @JsonProperty("Tier")
        private final long tier;
        @JsonProperty("Trend")
        private final long trend;
        @JsonProperty("Wins")
        private final long wins;

    }
}
