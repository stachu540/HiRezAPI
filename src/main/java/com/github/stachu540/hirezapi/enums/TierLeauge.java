package com.github.stachu540.hirezapi.enums;

import lombok.Getter;

/**
 * @author <a href="damian@stachuofficial.pl">Damian Staszewski</a>
 * @since 1.8
 */
@Getter
public enum TierLeauge {
  Qualifying(0),
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
  Master(26),
  Grandmaster(27);

  private final int id;
  private final String name;

  TierLeauge(final int id) {
    this.name = name().replace("_", " ");
    this.id = id;
  }

  public static TierLeauge getTier(int tier) {
    for (TierLeauge tl : values()) {
      if (tl.id == tier) {
        return tl;
      }
    }
    return Qualifying;
  }
}
