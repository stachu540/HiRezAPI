package hirez.smite.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

@Data
public class ItemRecommendation implements ReturnedMessage {
    @JsonProperty("Category")
    private final String category;
    @JsonProperty("Item")
    private final String name;
    @JsonProperty("Role")
    private final String role;
    private final long categoryValueId;
    private final long godId;
    private final String godName;
    @JsonProperty("icon_id")
    private final long iconId;
    @JsonProperty("item_id")
    private final long id;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    private final long roleValueId;

}
