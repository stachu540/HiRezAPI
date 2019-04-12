package hirez.api.object.statuspage;

import hirez.api.object.interfaces.IDObject;
import lombok.Data;

import java.util.Date;

@Data
public class Component implements IDObject<String> {
    private final String status;
    private final String name;
    private final Date createdAt;
    private final Date updatedAt;
    private final int position;
    private final String description;
    private final boolean showcase;
    private final String id;
    private final String groupId;
    private final String pageId;
    private final boolean group;
    private final boolean onlyShowIfDegraded;
}
