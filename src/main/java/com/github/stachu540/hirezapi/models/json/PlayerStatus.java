package com.github.stachu540.hirezapi.models.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.stachu540.hirezapi.annotations.Endpoint;
import com.github.stachu540.hirezapi.enums.PlayerState;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@Endpoint("getplayerstatus")
public class PlayerStatus extends Model {
    @JsonProperty("status") private PlayerState status;
    @JsonProperty("personal_status_message") private String statusMessage;
    @JsonProperty("Match") private long matchId;
    @JsonProperty("status_string") private String stringifyStatus;
}
