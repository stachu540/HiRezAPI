package hirez.api;

import hirez.api.object.interfaces.IDObject;

public enum Portal implements IDObject<Integer> {
    UNKNOWN(-1),
    HIREZ(1),
    STEAM(5),
    PS4(9),
    XBOX(10),
    SWITCH(22),
    DISCORD(25);

    private final Integer id;

    Portal(int id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }
}
