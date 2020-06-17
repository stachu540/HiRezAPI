package hirez.smite.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.adapters.TextToBoolean;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

import java.util.List;

@Data
public class Item implements ReturnedMessage {

    @TextToBoolean
    @JsonProperty("ActiveFlag")
    private final boolean active;
    @JsonProperty("ChildItemId")
    private final long childId;
    @JsonProperty("DeviceName")
    private final String name;
    private final long iconId;
    @JsonProperty("ItemDescription")
    private final Description description;
    @JsonProperty("itemIcon_URL")
    private final String iconURL;
    @JsonProperty("ItemId")
    private final long id;
    private final int itemTier;
    private final int price;
    private final String restrictedRoles;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    private final long rootItemId;
    private final String shortDesc;
    private final Boolean startingItem;
    private final String type;

    @Data
    public static class Description {
        private final String description;
        @JsonProperty("menuitems")
        private final List<DescriptionValue> menuItems;
        private final String secondaryDescription;
    }
}
