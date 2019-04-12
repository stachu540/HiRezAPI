package hirez.api;

import hirez.api.object.interfaces.IDObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Language implements IDObject<Integer> {
    English(1),
    German(2),
    French(3),
    Chinese(5),
    Spanish(7),
    Latin_Spanish(9),
    Portuguese(10),
    Russian(11),
    Polish(12),
    Turkish(13);

    private final Integer id;

    public String getName() {
        return name().replace("_", " ");
    }

    @Override
    public String toString() {
        return getName();
    }
}
