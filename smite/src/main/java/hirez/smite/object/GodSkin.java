package hirez.smite.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

@Data
public class GodSkin implements ReturnedMessage {
    @JsonProperty("godIcon_URL")
    private final String godIconUrl;
    private final long godId;
    private final String godName;
    @JsonProperty("godSkin_URL")
    private final String skinURL;
    private final String obtainability;
    private final int priceFavor;
    @JsonProperty("price_gems")
    private final int priceGems;
    @JsonProperty("ret_msg")
    private final String returnedMessage;
    @JsonProperty("skin_id1")
    private final long id1;
    @JsonProperty("skin_id2")
    private final long id2;
    @JsonProperty("skin_name")
    private final String name;

}
