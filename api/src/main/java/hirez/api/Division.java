package hirez.api;

public enum Division {
    Qualifying,
    Bronze_V,
    Bronze_IV,
    Bronze_III,
    Bronze_II,
    Bronze_I,
    Silver_V,
    Silver_IV,
    Silver_III,
    Silver_II,
    Silver_I,
    Gold_V,
    Gold_IV,
    Gold_III,
    Gold_II,
    Gold_I,
    Platinum_V,
    Platinum_IV,
    Platinum_III,
    Platinum_II,
    Platinum_I,
    Diamond_V,
    Diamond_IV,
    Diamond_III,
    Diamond_II,
    Diamond_I,
    Master,
    Grandmaster;

    private final String value =
            name().replace('_', ' ');

    @Override
    public String toString() {
        return value;
    }
}
