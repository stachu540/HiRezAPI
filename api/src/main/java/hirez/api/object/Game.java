package hirez.api.object;

import hirez.api.object.interfaces.IDObject;
import lombok.Data;

@Data
public class Game implements IDObject<String> {
    private final String id;
    private final String name;
}
