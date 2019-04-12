
package hirez.paladins.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

@Data
public class Item implements ReturnedMessage {
    private final long championId;
    private final String description;
    @JsonProperty("DeviceName")
    private final String name;
    private final long iconId;
    @JsonProperty("itemIcon_URL")
    private final String iconURL;
    @JsonProperty("ItemId")
    private final long id;
    @JsonProperty("item_type")
    private final String type;
    private final long price;
    private final long rechargeSeconds;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    @JsonProperty("ShortDesc")
    private final String shortDescription;
    private final long talentRewardLevel;
}
