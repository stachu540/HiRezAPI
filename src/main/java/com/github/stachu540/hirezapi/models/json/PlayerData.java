package com.github.stachu540.hirezapi.models.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.stachu540.hirezapi.annotations.Endpoint;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Endpoint("getplayer")
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerData extends Player {
  private Achievments achievments;
  private PlayerStatus status;

  public void setAchievments(Achievments achievments) {
    this.achievments = (this.achievments != null) ? this.achievments : achievments;
  }

  public void setStatus(PlayerStatus status) {
    this.status = (this.status != null) ? this.status : status;
  }
}
