package com.github.stachu540.hirezapi.enums.queues;

import lombok.Getter;

@Getter
public enum SmiteQ implements Queue {
  Conquest(426, "Conquest"),
  Arena(435, "Arena"),
  Clash(466, "Clash"),
  Joust(448, "Joust"),
  Assault(445, "Assault"),
  Siege(459, "Siege"),
  MOTD(434, "Match of the Day"),
  Adventures(489, "Adventures"),
  Conquest_AI_Easy(476, "Conquest Coop (Easy)"),
  Conquest_AI_Medium(461, "Conquest Coop (Medium)"),
  Arena_AI_Easy(457, "Arena Coop (Easy)"),
  Arena_AI_Medium(468, "Arena Coop (Medium)"),
  Clash_AI_Easy(478, "Clash Coop (Easy)"),
  Clash_AI_Medium(469, "Clash Coop (Medium)"),
  Joust_AI_Easy(474, "Joust Coop (Easy)"),
  Joust_AI_Medium(461, "Joust Coop (Medium)"),
  Assault_AI_Easy(481, "Assault Coop (Easy)"),
  Assault_AI_Medium(454, "Assault Coop (Medium)"),
  Conquest_League(451, "Conquest League"),
  Joust_League(450, "Joust League"),
  Joust_Duel(440, "Duel League"),
  Basic_Tutorial(436, "Basic Tutorial"),
  Clash_Tutorial(471, "Clash Tutorial"),
  Conquest_Tutorial(463, "Conquest Tutorial"),
  Arena_Tutorial(462, "Arena Tutorial"),
  Arena_Training(483, "Arena Training"),
  Joust_Training(482, "Joust Training"),
  Conquest_Practice_Easy(458, "Conquest vs AI (Easy)"),
  Conquest_Practice_Medium(475, "Conquest vs AI (Medium)"),
  Arena_Practice_Easy(443, "Arena vs AI (Easy)"),
  Arena_Practice_Medium(472, "Arena vs AI (Medium)"),
  Clash_Practice_Easy(470, "Clash vs AI (Easy)"),
  Clash_Practice_Medium(477, "Clash vs AI (Medium)"),
  Joust_Practice_Easy(464, "Joust vs AI (Easy)"),
  Joust_Practice_Medium(473, "Joust vs AI (Medium)"),
  Assault_Practice_Easy(479, "Assault vs AI (Easy)"),
  Assault_Practice_Medium(480, "Assault vs AI (Medium)"),
  Jungle_Practice(444, "Jungle Practice"),
  Conquest_Custom(429, "Conquest (Custom)"),
  Arena_Custom(438, "Arena (Custom)"),
  Clash_Custom(467, "Clash (Custom)"),
  Joust_Custom(441, "Joust (Custom)"),
  Assault_Custom(446, "Assault (Custom)"),
  Siege_Custom(460, "Siege (Custom)"),
  Adventures_Custom(490, "Adventures (Custom)");

  private final int id;
  private final String name;

  SmiteQ(int id, String name) {
    this.name = name;
    this.id = id;
  }
}
