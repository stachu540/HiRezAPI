package hirezapi.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import hirezapi.enums.RankedTier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Player extends PlayerDetails {
  @JsonProperty("Avatar_URL")
  private String avatar;

  @JsonProperty("Created_Datetime")
  private Instant createdAt;

  @JsonProperty("Id")
  private long id;

  @JsonProperty("Last_Login_Datetime")
  private Instant lastLogin;

  @JsonProperty("Level")
  private int level;

  @JsonProperty("MasteryLevel")
  private int masteryLevel;

  @JsonProperty("Personal_Status_Message")
  private String statusMessage;

  @JsonProperty("RankedConquest")
  private Ranked rankedConquest;

  @JsonProperty("RankedDuel")
  private Ranked rankedDuel;

  @JsonProperty("RankedJoust")
  private Ranked rankedJoust;

  @JsonProperty("Region")
  private String region;

  @JsonProperty("TeamId")
  private int teamId;

  @JsonProperty("Team_Name")
  private String teamName;

  @JsonProperty("Tier_Conquest")
  private RankedTier tierConquest;

  @JsonProperty("Tier_Duel")
  private RankedTier tierDuel;

  @JsonProperty("Tier_Joust")
  private RankedTier tierJoust;

  @JsonProperty("Total_Achievements")
  private int totalAchievements;

  @JsonProperty("Total_Worshippers")
  private int totalWorshippers;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  @JsonIgnoreProperties(ignoreUnknown = true)
  @JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
  public static class Ranked extends PlayerDetails {
    @JsonProperty("Points")
    private int points;
    @JsonProperty("PrevRank")
    private int prevRank;
    @JsonProperty("Rank")
    private int rank;
    @JsonProperty("Season")
    private int season;
    @JsonProperty("Tier")
    private RankedTier tier;
  }
}
