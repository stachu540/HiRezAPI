package hirez.api.object.statuspage;

import hirez.api.object.interfaces.IDObject;
import lombok.Data;

import java.util.Date;

@Data
public class Page implements IDObject<String> {
    private final String id;
    private final String name;
    private final Date updatedAt;
    private final String url;
    private final String timezone;
}
