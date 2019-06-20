package hirez.paladins.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import hirez.api.object.interfaces.ReturnedMessage;
import lombok.Data;

import java.util.List;

@Data
public class PlayerLoadout implements ReturnedMessage {
    @JsonProperty("ChampionId")
    private final long championId;
    @JsonProperty("ChampionName")
    private final String championName;
    @JsonProperty("DeckId")
    private final long deckId;
    @JsonProperty("DeckName")
    private final String deckName;
    @JsonProperty("LoadoutItems")
    private final List<Item> loadoutItems;
    private final long playerId;
    private final String playerName;
    @JsonProperty("ret_msg")
    private final String returnedMessage;

    @Data
    public static class Item {
        @JsonProperty("ItemId")
        private final long id;
        @JsonProperty("ItemName")
        private final String name;
        @JsonProperty("Points")
        private final int points;

    }
}
