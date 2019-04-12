package hirez.api.object.statuspage;

import lombok.Data;

@Data
public class AffectedComponent {
    private final String code;
    private final String name;
    private final String oldStatus;
    private final String newStatus;
}
