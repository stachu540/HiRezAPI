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
    @JsonProperty("IconId")
    private final long iconId;
    @JsonProperty("ItemDescription")
    private final Description description;
    @JsonProperty("ItemId")
    private final long id;
    @JsonProperty("ItemTier")
    private final int itemTier;
    @JsonProperty("Price")
    private final int price;
    @JsonProperty("RestrictedRoles")
    private final String restrictedRoles;
    @JsonProperty("RootItemId")
    private final long rootItemId;
    @JsonProperty("ShortDesc")
    private final String shortDescription;
    @JsonProperty("StartingItem")
    private final boolean startingItem;
    @JsonProperty("Type")
    private final String type;
    @JsonProperty("itemIcon_URL")
    private final String iconURL;
    @JsonProperty("ret_msg")
    private final String returnedMessage;

    @Data
    public static class Description {
        @JsonProperty("Description")
        private final String description;
        @JsonProperty("Menuitems")
        private final List<Index> menuItems;
        @JsonProperty("SecondaryDescription")
        private final String secondaryDescription;

        @Data
        public static class Index {
            @JsonProperty("Description")
            private final String description;
            @JsonProperty("Value")
            private final String value;
        }
    }
}
