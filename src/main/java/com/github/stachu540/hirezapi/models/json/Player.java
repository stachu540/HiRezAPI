package com.github.stachu540.hirezapi.models.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.stachu540.hirezapi.annotations.Endpoint;
import com.github.stachu540.hirezapi.enums.TierLeauge;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.text.ParseException;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@Endpoint("getplayer")
public class Player extends Model {
  @JsonProperty("Avatar_URL")
  private String avatar;

  @JsonProperty("Created_Datetime")
  private Date createdAt;

  @JsonProperty("Id")
  private long id;

  @JsonProperty("Last_Login_Datetime")
  private Date lastLogin;

  @JsonProperty("Leaves")
  private int leaves;

  @JsonProperty("Level")
  private int level;

  @JsonProperty("Losses")
  private int losses;

  @JsonProperty("MasteryLevel")
  private int masteryLevel;

  @JsonProperty("Name")
  private String name;

  @JsonProperty("Personal_Status_Message")
  private String statusMessage;

  @JsonProperty("Region")
  private String region;

  @JsonProperty("TeamId")
  private int teamId;

  @JsonProperty("Team_Name")
  private String teamName;

  @JsonProperty("Tier_Conquest")
  private TierLeauge tierConquest;

  @JsonProperty("Tier_Duel")
  private TierLeauge tierDuel;

  @JsonProperty("Tier_Joust")
  private TierLeauge tierJoust;

  @JsonProperty("Total_Achievements")
  private int totalAchievements;

  @JsonProperty("Total_Worshippers")
  private int totalWorshippers;

  @JsonProperty("Wins")
  private int wins;

  private TierLeauge setTierLeague(int tier) {
    return TierLeauge.getTier(tier);
  }

  public void setCreatedAt(String timestamp) {
    try {
      this.createdAt = parse(timestamp);
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  public void setLastLogin(String timestamp) {
    try {
      this.lastLogin = parse(timestamp);
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  public void setTierConquest(int tier) {
    this.tierConquest = setTierLeague(tier);
  }

  public void setTierJoust(int tier) {
    this.tierJoust = setTierLeague(tier);
  }

  public void setTierDuel(int tier) {
    this.tierDuel = setTierLeague(tier);
  }
}
