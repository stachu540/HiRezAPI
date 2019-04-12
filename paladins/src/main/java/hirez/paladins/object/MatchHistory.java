
package hirez.paladins.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.adapters.DateTimeFormat;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

import java.util.Date;

@Data
@SuppressWarnings("unused")
public class MatchHistory implements ReturnedMessage {

    @JsonProperty("Active_1")
    private String active1;
    @JsonProperty("Active_2")
    private String active2;
    @JsonProperty("Active_3")
    private String active3;
    @JsonProperty("Active_4")
    private String active4;
    @JsonProperty("ActiveId1")
    private long activeId1;
    @JsonProperty("ActiveId2")
    private long activeId2;
    @JsonProperty("ActiveId3")
    private long activeId3;
    @JsonProperty("ActiveId4")
    private long activeId4;
    @JsonProperty("ActiveLevel1")
    private int activeLevel1;
    @JsonProperty("ActiveLevel2")
    private int activeLevel2;
    @JsonProperty("ActiveLevel3")
    private int activeLevel3;
    @JsonProperty("ActiveLevel4")
    private int activeLevel4;
    @JsonProperty("Assists")
    private int assists;
    @JsonProperty("Champion")
    private String champion;
    @JsonProperty("ChampionId")
    private long championId;
    @JsonProperty("Creeps")
    private int creeps;
    @JsonProperty("Damage")
    private int damage;
    @JsonProperty("Damage_Bot")
    private int damageBot;
    @JsonProperty("Damage_Done_In_Hand")
    private int damageDoneInHand;
    @JsonProperty("Damage_Mitigated")
    private int damageMitigated;
    @JsonProperty("Damage_Structure")
    private int damageStructure;
    @JsonProperty("Damage_Taken")
    private int damageTaken;
    @JsonProperty("Damage_Taken_Magical")
    private int damageTakenMagical;
    @JsonProperty("Damage_Taken_Physical")
    private int damageTakenPhysical;
    @JsonProperty("Deaths")
    private int deaths;
    @JsonProperty("Distance_Traveled")
    private int distanceTraveled;
    @JsonProperty("Gold")
    private int gold;
    @JsonProperty("Healing")
    private int healing;
    @JsonProperty("Healing_Bot")
    private int healingBot;
    @JsonProperty("Healing_Player_Self")
    private int healingPlayerSelf;
    @JsonProperty("Item_1")
    private String item1;
    @JsonProperty("Item_2")
    private String item2;
    @JsonProperty("Item_3")
    private String item3;
    @JsonProperty("Item_4")
    private String item4;
    @JsonProperty("Item_5")
    private String item5;
    @JsonProperty("Item_6")
    private String item6;
    @JsonProperty("ItemId1")
    private long itemId1;
    @JsonProperty("ItemId2")
    private long itemId2;
    @JsonProperty("ItemId3")
    private long itemId3;
    @JsonProperty("ItemId4")
    private long itemId4;
    @JsonProperty("ItemId5")
    private long itemId5;
    @JsonProperty("ItemId6")
    private long itemId6;
    @JsonProperty("ItemLevel1")
    private int itemLevel1;
    @JsonProperty("ItemLevel2")
    private int itemLevel2;
    @JsonProperty("ItemLevel3")
    private int itemLevel3;
    @JsonProperty("ItemLevel4")
    private int itemLevel4;
    @JsonProperty("ItemLevel5")
    private int itemLevel5;
    @JsonProperty("ItemLevel6")
    private int itemLevel6;
    @JsonProperty("Killing_Spree")
    private int killingSpree;
    @JsonProperty("Kills")
    private int kills;
    @JsonProperty("Level")
    private int level;
    @JsonProperty("Map_Game")
    private String mapGame;
    @JsonProperty("Match")
    private long id;
    @JsonProperty("Match_Queue_Id")
    private int queueId;
    @DateTimeFormat
    @JsonProperty("Match_Time")
    private Date matchTime;
    @JsonProperty("Minutes")
    private long minutes;
    @JsonProperty("Multi_kill_Max")
    private int multiKillMax;
    @JsonProperty("Objective_Assists")
    private int objectiveAssists;
    private long playerId;
    private String playerName;
    @JsonProperty("Queue")
    private String queue;
    @JsonProperty("Region")
    private String region;
    @JsonProperty("ret_msg")
    private String returnedMessage;
    @JsonProperty("Skin")
    private String skin;
    @JsonProperty("SkinId")
    private long skinId;
    @JsonProperty("Surrendered")
    private int surrendered;
    @JsonProperty("TaskForce")
    private int taskForce;
    @JsonProperty("Team1Score")
    private int team1Score;
    @JsonProperty("Team2Score")
    private int team2Score;
    @JsonProperty("Time_In_Match_Seconds")
    private int timeInMatchSeconds;
    @JsonProperty("Wards_Placed")
    private int wardsPlaced;
    @JsonProperty("Win_Status")
    private String winStatus;
    @JsonProperty("Winning_TaskForce")
    private int winningTaskForce;

}
