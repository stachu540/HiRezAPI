package hirezapi.paladins.enums;

import hirezapi.enums.Queue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaladinsQueue implements Queue {
  LIVE_Casual(424, "LIVE Casual", false),
  LIVE_TDM(469, "LIVE Team Deathmatch", false),
  LIVE_Onslaught(452, "LIVE Onslaught", false),
  LIVE_Competitive(428, "LIVE Competitive", false),
  Classic_Siege(465, "Classic Siege", false),
  LIVE_Practice_Siege(425, "LIVE Practice Siege", false),
  LIVE_Onslaught_Practice(453, "LIVE Onslaught Practice", false),
  LIVE_TDM_Practice(470, "LIVE Team Deathmatch Practice", false),
  LIVE_Test_Maps(445, "LIVE Test Maps", false),
  LIVE_PBG_Solo(474, "LIVE Battlegrounds Solo", false),
  LIVE_PBG_Duo(475, "LIVE Battlegrounds Duo", false),
  LIVE_PBG_Squad(476, "LIVE Battlegrounds Squad", false),
  Custom_T_Magistrates_Archives(472, "Magistrate's Archives [TDM] (Custom)", false),
  Custom_T_Trade_District(468, "Trade District [TDM] (Custom)", false),
  Custom_S_Stone_Keep(423, "Stone Keep [Siege] (Custom)", false),
  Custom_T_Foremans_Rise(471, "Foreman's Rise [TDM] (Custom)", false),
  Custom_S_Frog_Isle(433, "Frog Isle [Siege] (Custom)", false),
  Custom_S_Fish_Market(431, "Fish Market [Siege] (Custom)", false),
  Custom_S_Brightmarsh(458, "Brightmarsh [Siege] (Custom)", false),
  Custom_S_Timber_Mill(430, "Timber Mill [Siege] (Custom)", false),
  Custom_S_Serpent_Beach(440, "Serpent Beach [Siege] (Custom)", false),
  Custom_S_Jaguar_Falls(438, "Jaguar Falls [Siege] (Custom)", false),
  Custom_S_Splitstone_Quarry(459, "Splitstone Quarry [Siege] (Custom)", false),
  Custom_O_Magistrates_Archives(464, "Magistrate's Archives [Onslaught] (Custom)", false),
  Custom_S_Frozen_Guard(432, "Frozen Guard [Siege] (Custom)", false),
  Custom_O_Foremans_Rise(462, "Foreman's Rise [Onslaught] (Custom)", false),
  Custom_S_Ice_Mines(439, "Ice Mines [Siege] (Custom)", false),
  Custom_O_Primal_Court(455, "Primal Court [Onslaught] (Custom)", false),
  Custom_O_Snowfall_Junction(454, "Snowfall Junction [Onslaught] (Custom)", false);

  private final int id;
  private final String name;
  private final boolean ranked;
}
