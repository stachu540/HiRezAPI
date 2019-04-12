
package hirez.paladins.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

@Data
public class ChampionSkin implements ReturnedMessage {
    private final long championId;
    private final String championName;
    private final String rarity;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    @JsonProperty("skin_id1")
    private final long id1;
    @JsonProperty("skin_id2")
    private final long id2;
    @JsonProperty("skin_name")
    private final String name;
    @JsonProperty("skin_name_english")
    private final String englishName;
}
