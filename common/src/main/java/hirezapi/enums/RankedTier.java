package hirezapi.enums;

import lombok.Getter;

/**
 * @author <a href="damian@stachuofficial.pl">Damian Staszewski</a>
 * @since 1.0
 */
public enum RankedTier {
    Qualifying,
    Bronze_5,
    Bronze_4,
    Bronze_3,
    Bronze_2,
    Bronze_1,
    Silver_5,
    Silver_4,
    Silver_3,
    Silver_2,
    Silver_1,
    Gold_5,
    Gold_4,
    Gold_3,
    Gold_2,
    Gold_1,
    Platinum_5,
    Platinum_4,
    Platinum_3,
    Platinum_2,
    Platinum_1,
    Diamond_5,
    Diamond_4,
    Diamond_3,
    Diamond_2,
    Diamond_1,
    Master,
    Grandmaster;

    @Getter
    private final String name = name().replace("_", " ");

    public static RankedTier get(int tier) {
        for (RankedTier tl : values()) {
            if (tl.ordinal() == tier) {
                return tl;
            }
        }
        return Qualifying;
    }
}
