package hirez.paladins.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

@Data
public class Item implements ReturnedMessage {
    @JsonProperty("Description")
    private final String description;
    @JsonProperty("DeviceName")
    private final String name;
    @JsonProperty("IconId")
    private final long iconId;
    @JsonProperty("ItemId")
    private final long id;
    @JsonProperty("Price")
    private final long price;
    @JsonProperty("ShortDesc")
    private final String shortDescription;
    private final long championId;
    @JsonProperty("itemIcon_URL")
    private final String iconURL;
    @JsonProperty("item_type")
    private final String type;
    private final long rechargeSeconds;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    private final long talentRewardLevel;
}
