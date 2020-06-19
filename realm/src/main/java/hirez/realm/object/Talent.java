package hirez.realm.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

/**
 * @deprecated This endpoint is not exist
 */
@Data
@Deprecated
public class Talent implements ReturnedMessage {
    private final String categoryName;
    @JsonProperty("item_id")
    private final long id;
    private final long lootTableItemId;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    @JsonProperty("talent_description")
    private final String description;
    @JsonProperty("talent_name")
    private final String name;

}
