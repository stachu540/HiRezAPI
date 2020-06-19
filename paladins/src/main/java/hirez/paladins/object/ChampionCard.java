package hirez.paladins.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.adapters.DurationTime;
import hirez.api.object.adapters.TextToBoolean;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

import java.time.Duration;

@Data
public class ChampionCard implements ReturnedMessage {
    @TextToBoolean
    @JsonProperty("active_flag_activation_schedule")
    private final boolean activationScheduled;
    @TextToBoolean
    @JsonProperty("active_flag_lti")
    private final boolean lti;
    @JsonProperty("card_description")
    private final String description;
    @JsonProperty("card_id1")
    private final long id1;
    @JsonProperty("card_id2")
    private final long id2;
    @JsonProperty("card_name")
    private final String name;
    @JsonProperty("card_name_english")
    private final String englishName;
    @JsonProperty("championCard_URL")
    private final String cardUrl;
    @JsonProperty("championIcon_URL")
    private final String iconURL;
    private final long championId;
    private final String championName;
    @TextToBoolean
    private final boolean exclusive;
    private final int rank;
    private final String rarity;
    @DurationTime
    @JsonProperty("recharge_seconds")
    private final Duration recharge;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
}
