package hirez.realm;

import hirez.api.object.interfaces.Queue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Queues implements Queue {
    SOLO(474, "Solo"),
    DUO(475, "Duo"),
    SQUAD(476, "Squad");

    @Getter
    private final Integer id;
    private final String named;

    public static Queues fromId(int id) throws IllegalAccessException {
        for (Queues queue : values()) {
            if (queue.id == id) {
                return queue;
            }
        }

        throw new IllegalAccessException("Unknown Queue ID: " + id);
    }

    @Override
    public String getName() {
        return named;
    }

    @Override
    public boolean isRanked() {
        return name().startsWith("COMPETITIVE_");
    }
}
