
package hirez.smite.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class MatchHistory {

    @JsonProperty("Active_1")
    private final String active1;
    @JsonProperty("Active_2")
    private final String active2;
    @JsonProperty("Active_3")
    private final Object active3;
    @JsonProperty("ActiveId1")
    private final long activeId1;
    @JsonProperty("ActiveId2")
    private final long activeId2;
    @JsonProperty("Assists")
    private final long assists;
    @JsonProperty("Ban1")
    private final String ban1;
    @JsonProperty("Ban10")
    private final String ban10;
    @JsonProperty("Ban10Id")
    private final long ban10Id;
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
    @JsonProperty("Creeps")
    private final long creeps;
    @JsonProperty("Damage")
    private final long damage;
    @JsonProperty("Damage_Bot")
    private final long damageBot;
    @JsonProperty("Damage_Done_In_Hand")
    private final long damageDoneInHand;
    @JsonProperty("Damage_Mitigated")
    private final long damageMitigated;
    @JsonProperty("Damage_Structure")
    private final long damageStructure;
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
    @JsonProperty("First_Ban_Side")
    private final String firstBanSide;
    @JsonProperty("God")
    private final String god;
    @JsonProperty("GodId")
    private final long godId;
    @JsonProperty("Gold")
    private final long gold;
    @JsonProperty("Healing")
    private final long healing;
    @JsonProperty("Healing_Bot")
    private final long healingBot;
    @JsonProperty("Healing_Player_Self")
    private final long healingPlayerSelf;
    @JsonProperty("Item_1")
    private final String item1;
    @JsonProperty("Item_2")
    private final String item2;
    @JsonProperty("Item_3")
    private final String item3;
    @JsonProperty("Item_4")
    private final String item4;
    @JsonProperty("Item_5")
    private final String item5;
    @JsonProperty("Item_6")
    private final String item6;
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
    @JsonProperty("Killing_Spree")
    private final long killingSpree;
    @JsonProperty("Kills")
    private final long kills;
    @JsonProperty("Level")
    private final long level;
    @JsonProperty("Map_Game")
    private final String mapGame;
    @JsonProperty("Match")
    private final long match;
    @JsonProperty("Match_Queue_Id")
    private final long matchQueueId;
    @JsonProperty("Match_Time")
    private final String matchTime;
    @JsonProperty("Minutes")
    private final long minutes;
    @JsonProperty("Multi_kill_Max")
    private final long multiKillMax;
    @JsonProperty("Objective_Assists")
    private final long objectiveAssists;
    private final long playerId;
    private final String playerName;
    @JsonProperty("Queue")
    private final String queue;
    @JsonProperty("Region")
    private final String region;
    @JsonProperty("ret_msg")
    private final Object retMsg;
    @JsonProperty("Skin")
    private final String skin;
    @JsonProperty("SkinId")
    private final long skinId;
    @JsonProperty("Surrendered")
    private final long surrendered;
    @JsonProperty("TaskForce")
    private final long taskForce;
    @JsonProperty("Team1Score")
    private final long team1Score;
    @JsonProperty("Team2Score")
    private final long team2Score;
    @JsonProperty("Time_In_Match_Seconds")
    private final long timeInMatchSeconds;
    @JsonProperty("Wards_Placed")
    private final long wardsPlaced;
    @JsonProperty("Win_Status")
    private final String winStatus;
    @JsonProperty("Winning_TaskForce")
    private final long winningTaskForce;

}
