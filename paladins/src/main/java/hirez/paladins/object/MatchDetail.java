package hirez.paladins.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.MergedAccount;
import hirez.api.object.adapters.DateTimeFormat;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

import java.util.Date;

@Data
public class MatchDetail implements ReturnedMessage {
    private final int accountLevel;

    @JsonProperty("ActiveId1")
    private final long activeId1;
    @JsonProperty("ActiveId2")
    private final long activeId2;
    @JsonProperty("ActiveId3")
    private final long activeId3;
    @JsonProperty("ActiveId4")
    private final long activeId4;

    @JsonProperty("ActiveLevel1")
    private final long activeLevel1;
    @JsonProperty("ActiveLevel2")
    private final long activeLevel2;
    @JsonProperty("ActiveLevel3")
    private final long activeLevel3;
    @JsonProperty("ActiveLevel4")
    private final long activeLevel4;

    private final long activePlayerId;
    @JsonProperty("Assists")
    private final long assists;

    @JsonProperty("Ban_1")
    private final String ban1;
    @JsonProperty("Ban_2")
    private final String ban2;
    @JsonProperty("Ban_3")
    private final String ban3;
    @JsonProperty("Ban_4")
    private final String ban4;
    @JsonProperty("BanId1")
    private final long banId1;
    @JsonProperty("BanId2")
    private final long banId2;
    @JsonProperty("BanId3")
    private final long banId3;
    @JsonProperty("BanId4")
    private final long banId4;

    @JsonProperty("Camps_Cleared")
    private final int campsCleared;
    @JsonProperty("ChampionId")
    private final long championId;
    @JsonProperty("Damage_Bot")
    private final int damageBot;
    @JsonProperty("Damage_Done_In_Hand")
    private final int damageDoneInHand;
    @JsonProperty("Damage_Done_Magical")
    private final int damageDoneMagical;
    @JsonProperty("Damage_Done_Physical")
    private final int damageDonePhysical;
    @JsonProperty("Damage_Mitigated")
    private final int damageMitigated;
    @JsonProperty("Damage_Player")
    private final int damagePlayer;
    @JsonProperty("Damage_Taken")
    private final int damageTaken;
    @JsonProperty("Damage_Taken_Magical")
    private final int damageTakenMagical;
    @JsonProperty("Damage_Taken_Physical")
    private final int damageTakenPhysical;
    @JsonProperty("Deaths")
    private final int deaths;
    @JsonProperty("Distance_Traveled")
    private final int distanceTraveled;
    @DateTimeFormat
    @JsonProperty("Entry_Datetime")
    private final Date entryDatetime;
    @JsonProperty("Final_Match_Level")
    private final int finalMatchLevel;
    @JsonProperty("Gold_Earned")
    private final int goldEarned;
    @JsonProperty("Gold_Per_Minute")
    private final int goldPerMinute;
    private final String hasReplay;
    @JsonProperty("Healing")
    private final int healing;
    @JsonProperty("Healing_Bot")
    private final int healingBot;
    @JsonProperty("Healing_Player_Self")
    private final int healingPlayerSelf;
    @JsonProperty("hz_gamer_tag")
    private final String hiRezGamerTag;
    @JsonProperty("hz_player_name")
    private final String hiRezPlayerName;
    @JsonProperty("Item_Active_1")
    private final String itemActive1;
    @JsonProperty("Item_Active_2")
    private final String itemActive2;
    @JsonProperty("Item_Active_3")
    private final String itemActive3;
    @JsonProperty("Item_Active_4")
    private final String itemActive4;
    @JsonProperty("ItemId1")
    private final long itemId1;
    @JsonProperty("ItemId2")
    private final long itemId2;
    @JsonProperty("ItemId3")
    private final long itemId3;
    @JsonProperty("ItemId4")
    private final long itemId4;
    @JsonProperty("ItemId5")
    private final long itemId5;
    @JsonProperty("ItemId6")
    private final long itemId6;
    @JsonProperty("ItemLevel1")
    private final int itemLevel1;
    @JsonProperty("ItemLevel2")
    private final int itemLevel2;
    @JsonProperty("ItemLevel3")
    private final int itemLevel3;
    @JsonProperty("ItemLevel4")
    private final int itemLevel4;
    @JsonProperty("ItemLevel5")
    private final int itemLevel5;
    @JsonProperty("ItemLevel6")
    private final int itemLevel6;
    @JsonProperty("Item_Purch_1")
    private final String itemPurch1;
    @JsonProperty("Item_Purch_2")
    private final String itemPurch2;
    @JsonProperty("Item_Purch_3")
    private final String itemPurch3;
    @JsonProperty("Item_Purch_4")
    private final String itemPurch4;
    @JsonProperty("Item_Purch_5")
    private final String itemPurch5;
    @JsonProperty("Item_Purch_6")
    private final String itemPurch6;
    @JsonProperty("Killing_Spree")
    private final int killingSpree;
    @JsonProperty("Kills_Bot")
    private final int killsBot;
    @JsonProperty("Kills_Double")
    private final int killsDouble;
    @JsonProperty("Kills_Fire_Giant")
    private final int killsFireGiant;
    @JsonProperty("Kills_First_Blood")
    private final int killsFirstBlood;
    @JsonProperty("Kills_Gold_Fury")
    private final int killsGoldFury;
    @JsonProperty("Kills_Penta")
    private final int killsPenta;
    @JsonProperty("Kills_Phoenix")
    private final int killsPhoenix;
    @JsonProperty("Kills_Player")
    private final int killsPlayer;
    @JsonProperty("Kills_Quadra")
    private final int killsQuadra;
    @JsonProperty("Kills_Siege_Juggernaut")
    private final int killsSiegeJuggernaut;
    @JsonProperty("Kills_Single")
    private final int killsSingle;
    @JsonProperty("Kills_Triple")
    private final int killsTriple;
    @JsonProperty("Kills_Wild_Juggernaut")
    private final int killsWildJuggernaut;
    @JsonProperty("League_Losses")
    private final int leagueLosses;
    @JsonProperty("League_Points")
    private final int leaguePoints;
    @JsonProperty("League_Tier")
    private final int leagueTier;
    @JsonProperty("League_Wins")
    private final int leagueWins;
    @JsonProperty("Map_Game")
    private final String mapGame;
    @JsonProperty("Mastery_Level")
    private final int masteryLevel;
    @JsonProperty("Match")
    private final long matchId;
    @JsonProperty("Match_Duration")
    private final long matchDuration;
    @JsonProperty("match_queue_id")
    private final int matchQueueId;
    @JsonProperty("MergedPlayers")
    private final MergedAccount[] mergedPlayers;
    @JsonProperty("Minutes")
    private final long minutes;
    @JsonProperty("Multi_kill_Max")
    private final int multiKillMax;
    private final String name;
    @JsonProperty("Objective_Assists")
    private final int objectiveAssists;
    @JsonProperty("PartyId")
    private final long partyId;
    @JsonProperty("Platform")
    private final String platform;
    private final long playerId;
    private final String playerName;
    private final int playerPortalId;
    private final long playerPortalUserId;
    @JsonProperty("Rank_Stat_League")
    private final int rankStatLeague;
    @JsonProperty("Reference_Name")
    private final String referenceName;
    @JsonProperty("Region")
    private final String region;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    @JsonProperty("Skin")
    private final String skin;
    @JsonProperty("SkinId")
    private final long skinId;
    @JsonProperty("Structure_Damage")
    private final int structureDamage;
    @JsonProperty("Surrendered")
    private final int surrendered;
    @JsonProperty("TaskForce")
    private final int taskForce;
    @JsonProperty("Team1Score")
    private final int team1Score;
    @JsonProperty("Team2Score")
    private final int team2Score;
    @JsonProperty("TeamId")
    private final long teamId;
    @JsonProperty("Team_Name")
    private final String teamName;
    @JsonProperty("Time_In_Match_Seconds")
    private final long timeInMatchSeconds;
    @JsonProperty("Towers_Destroyed")
    private final int towersDestroyed;
    @JsonProperty("Wards_Placed")
    private final int wardsPlaced;
    @JsonProperty("Win_Status")
    private final String winStatus;
    @JsonProperty("Winning_TaskForce")
    private final int winningTaskForce;

}
