package hirez.smite.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.MergedAccount;
import hirez.api.object.adapters.DateTimeFormat;
import hirez.api.object.adapters.DurationTime;
import hirez.api.object.adapters.TextToBoolean;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Duration;
import java.util.Date;

@Data
public class MatchDetail implements ReturnedMessage {
    @JsonProperty("Account_Level")
    private final int accountLevel;

    @JsonProperty("ActiveId1")
    private final long active1Id;
    @JsonProperty("ActiveId2")
    private final long active2Id;
    @JsonProperty("ActiveId3")
    private final long active3Id;
    @JsonProperty("ActiveId4")
    private final long active4Id;

    @JsonProperty("ActivePlayerId")
    private final long activePlayerId;

    @JsonProperty("Assists")
    private final long assists;

    @JsonProperty("Ban1")
    private final String ban1;
    @JsonProperty("Ban1Id")
    private final long ban1Id;
    @JsonProperty("Ban2")
    private final String ban2;
    @JsonProperty("Ban2Id")
    private final long ban2Id;
    @JsonProperty("Ban3")
    private final String ban3;
    @JsonProperty("Ban3Id")
    private final long ban3Id;
    @JsonProperty("Ban4")
    private final String ban4;
    @JsonProperty("Ban4Id")
    private final long ban4Id;
    @JsonProperty("Ban5")
    private final String ban5;
    @JsonProperty("Ban5Id")
    private final long ban5Id;
    @JsonProperty("Ban6")
    private final String ban6;
    @JsonProperty("Ban6Id")
    private final long ban6Id;
    @JsonProperty("Ban7")
    private final String ban7;
    @JsonProperty("Ban7Id")
    private final long ban7Id;
    @JsonProperty("Ban8")
    private final String ban8;
    @JsonProperty("Ban8Id")
    private final long ban8Id;
    @JsonProperty("Ban9")
    private final String ban9;
    @JsonProperty("Ban9Id")
    private final long ban9Id;
    @JsonProperty("Ban10")
    private final String ban10;
    @JsonProperty("Ban10Id")
    private final long ban10Id;

    @JsonProperty("Camps_Cleared")
    private final long campsCleared;
    @JsonProperty("Conquest_Losses")
    private final long conquestLosses;
    @JsonProperty("Conquest_Points")
    private final long conquestPoints;
    @JsonProperty("Conquest_Tier")
    private final long conquestTier;
    @JsonProperty("Conquest_Wins")
    private final long conquestWins;
    @JsonProperty("Damage_Bot")
    private final long damageBot;
    @JsonProperty("Damage_Done_In_Hand")
    private final long damageDoneInHand;
    @JsonProperty("Damage_Done_Magical")
    private final long damageDoneMagical;
    @JsonProperty("Damage_Done_Physical")
    private final long damageDonePhysical;
    @JsonProperty("Damage_Mitigated")
    private final long damageMitigated;
    @JsonProperty("Damage_Player")
    private final long damagePlayer;
    @JsonProperty("Damage_Taken")
    private final long damageTaken;
    @JsonProperty("Damage_Taken_Magical")
    private final long damageTakenMagical;
    @JsonProperty("Damage_Taken_Physical")
    private final long damageTakenPhysical;
    @JsonProperty("Deaths")
    private final long deaths;
    @JsonProperty("Distance_Traveled")
    private final long distanceTraveled;
    @JsonProperty("Duel_Losses")
    private final long duelLosses;
    @JsonProperty("Duel_Points")
    private final long duelPoints;
    @JsonProperty("Duel_Tier")
    private final long duelTier;
    @JsonProperty("Duel_Wins")
    private final long duelWins;
    @DateTimeFormat
    @JsonProperty("Entry_Datetime")
    private final Date entryDatetime;
    @JsonProperty("Final_Match_Level")
    private final int finalMatchLevel;
    @JsonProperty("First_Ban_Side")
    private final String firstBanSide;
    @JsonProperty("GodId")
    private final long godId;
    @JsonProperty("Gold_Earned")
    private final long goldEarned;
    @JsonProperty("Gold_Per_Minute")
    private final long goldPerMinute;
    @JsonProperty("Healing")
    private final long healing;
    @JsonProperty("Healing_Bot")
    private final long healingBot;
    @JsonProperty("Healing_Player_Self")
    private final long healingPlayerSelf;
    @JsonProperty("ItemId1")
    private final long item1Id;
    @JsonProperty("ItemId2")
    private final long item2Id;
    @JsonProperty("ItemId3")
    private final long item3Id;
    @JsonProperty("ItemId4")
    private final long item4Id;
    @JsonProperty("ItemId5")
    private final long item5Id;
    @JsonProperty("ItemId6")
    private final long item6Id;
    @JsonProperty("Item_Active_1")
    private final String item1Active;
    @JsonProperty("Item_Active_2")
    private final String item2Active;
    @JsonProperty("Item_Active_3")
    private final String item3Active;
    @JsonProperty("Item_Active_4")
    private final String item4Active;
    @JsonProperty("Item_Purch_1")
    private final String item1Name;
    @JsonProperty("Item_Purch_2")
    private final String item2Name;
    @JsonProperty("Item_Purch_3")
    private final String item3Name;
    @JsonProperty("Item_Purch_4")
    private final String item4Name;
    @JsonProperty("Item_Purch_5")
    private final String item5Name;
    @JsonProperty("Item_Purch_6")
    private final String item6Name;
    @JsonProperty("Joust_Losses")
    private final long joustLosses;
    @JsonProperty("Joust_Points")
    private final long joustPoints;
    @JsonProperty("Joust_Tier")
    private final long joustTier;
    @JsonProperty("Joust_Wins")
    private final long joustWins;
    @JsonProperty("Killing_Spree")
    private final long killingSpree;
    @JsonProperty("Kills_Bot")
    private final long killsBot;
    @JsonProperty("Kills_Double")
    private final long killsDouble;
    @JsonProperty("Kills_Fire_Giant")
    private final long killsFireGiant;
    @JsonProperty("Kills_First_Blood")
    private final long killsFirstBlood;
    @JsonProperty("Kills_Gold_Fury")
    private final long killsGoldFury;
    @JsonProperty("Kills_Penta")
    private final long killsPenta;
    @JsonProperty("Kills_Phoenix")
    private final long killsPhoenix;
    @JsonProperty("Kills_Player")
    private final long killsPlayer;
    @JsonProperty("Kills_Quadra")
    private final long killsQuadra;
    @JsonProperty("Kills_Siege_Juggernaut")
    private final long killsSiegeJuggernaut;
    @JsonProperty("Kills_Single")
    private final long killsSingle;
    @JsonProperty("Kills_Triple")
    private final long killsTriple;
    @JsonProperty("Kills_Wild_Juggernaut")
    private final long killsWildJuggernaut;
    @JsonProperty("Map_Game")
    private final String mapGame;
    @JsonProperty("Mastery_Level")
    private final long masteryLevel;
    @JsonProperty("Match")
    private final long matchId;
    @DurationTime
    @JsonProperty("Match_Duration")
    private final Duration matchDuration;
    @JsonProperty("MergedPlayers")
    private final MergedAccount[] mergedPlayers;
    @JsonProperty("Minutes")
    private final int minutes;
    @JsonProperty("Multi_kill_Max")
    private final long multiKillMax;
    @JsonProperty("Objective_Assists")
    private final long objectiveAssists;
    @JsonProperty("PartyId")
    private final long partyId;
    @JsonProperty("Rank_Stat_Conquest")
    private final double rankStatConquest;
    @JsonProperty("Rank_Stat_Duel")
    private final long rankStatDuel;
    @JsonProperty("Rank_Stat_Joust")
    private final long rankStatJoust;
    @JsonProperty("Reference_Name")
    private final String referenceName;
    @JsonProperty("Region")
    private final String region;
    @JsonProperty("Skin")
    private final String skin;
    @JsonProperty("SkinId")
    private final long skinId;
    @JsonProperty("Structure_Damage")
    private final long structureDamage;
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
    @DurationTime
    @JsonProperty("Time_In_Match_Seconds")
    private final Duration timeInMatch;
    @JsonProperty("Towers_Destroyed")
    private final long towersDestroyed;
    @JsonProperty("Wards_Placed")
    private final long wardsPlaced;
    @JsonProperty("Win_Status")
    private final String winStatus;
    @JsonProperty("Winning_TaskForce")
    private final int winningTaskForce;
    @TextToBoolean
    @Accessors(fluent = true)
    @JsonProperty("hasReplay")
    private final boolean hasReplay;
    @JsonProperty("hz_gamer_tag")
    private final Object hzGamerTag;
    @JsonProperty("hz_player_name")
    private final String hzPlayerName;
    @JsonProperty("match_queue_id")
    private final long matchQueueId;
    private final String name;
    @JsonProperty("playerId")
    private final long playerId;
    @JsonProperty("playerName")
    private final String playerName;
    @JsonProperty("playerPortalId")
    private final int playerPortalId;
    @JsonProperty("playerPortalUserId")
    private final long playerPortalUserId;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
}
