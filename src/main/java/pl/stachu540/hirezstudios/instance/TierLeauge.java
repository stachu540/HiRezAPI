package pl.stachu540.hirezstudios.instance;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Damian Staszewski <damian@stachuofficial.pl>
 * @since 1.8
 */
public enum TierLeauge {
    Bronze_5(1),
    Bronze_4(2),
    Bronze_3(3),
    Bronze_2(4),
    Bronze_1(5),
    Silver_5(6),
    Silver_4(7),
    Silver_3(8),
    Silver_2(9),
    Silver_1(10),
    Gold_5(11),
    Gold_4(12),
    Gold_3(13),
    Gold_2(14),
    Gold_1(15),
    Platinum_5(16),
    Platinum_4(17),
    Platinum_3(18),
    Platinum_2(19),
    Platinum_1(20),
    Diamond_5(21),
    Diamond_4(22),
    Diamond_3(23),
    Diamond_2(24),
    Diamond_1(25),
    Masters(26);

    private int id;

    private static Map<Integer, TierLeauge> map = new HashMap<Integer, TierLeauge>();

    static {
        for(TierLeauge queue : TierLeauge.values()) {
            map.put(queue.id, queue);
        }
    }

    TierLeauge(final int id) {
        this.id = id;
    }

    public static TierLeauge valueOf(int id) {
        return map.get(id);
    }

    public final String getName() {
        return name().replace("_", " ");
    }
}
