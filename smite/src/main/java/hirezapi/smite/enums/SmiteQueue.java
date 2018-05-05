package hirezapi.smite.enums;

import hirezapi.enums.Queue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SmiteQueue implements Queue {
    Arena(435, "Arena", false),
    Joust(448, "Joust", false),
    Conquest(426, "Conquest", false),
    Assault(445, "Assault", false),
    Clash(466, "Clash", false),
    Adventures(495, "Adventure", false),
    Conquest_League(451, "Conquest League", true),
    MOTD(434, "Match of the Day", false),
    Siege(459, "Siege", false),
    Joust_League(450, "Joust League", true),
    Joust_Duel(440, "Duel League", true),
    Joust_Custom(441, "Joust (Custom)", true),
    Arena_AI_Easy(457, "Arena (vs AI) (Easy)", false),
    Arena_AI_Medium(468, "Arena (vs AI) (Medium)", false),
    Basic_Tutorial(436, "Basic Tutorial", false),
    Arena_Custom(438, "Arena (Custom)", false),
    Conquest_Custom(429, "Conquest (Custom)", false),
    Joust_Training(482, "Joust Training", false),
    Arena_Tutorial(462, "Arena Tutorial", false),
    Arena_Training(483, "Arena Training", false),
    Joust_AI_Easy(474, "Joust (vs AI) (Easy)", false),
    Joust_AI_Medium(456, "Joust (vs AI) (Medium)", false),
    Clash_AI_Easy(478, "Clash (vs AI) (Easy)", false),
    Clash_AI_Medium(469, "Clash (vs AI) (Medium)", false),
    Arena_Practice_Medium(472, "Arena Practice (Medium)", false),
    Assault_AI_Easy(481, "Assault (vs AI) (Easy)", false),
    Conquest_AI_Easy(476, "Conquest (vs AI) (Easy)", false),
    Clash_Tutorial(471, "Clash Tutorial", false),
    Joust_Practice_Medium(473, "Joust Practice (Medium)", false),
    Assault_Custom(446, "Assault (Custom)", false),
    Assault_AI_Medium(454, "Assault (vs AI) (Medium)", false),
    Arena_Practice_Easy(443, "Arena Practice (Easy)", false),
    Clash_Custom(467, "Clash (Custom)", false),
    Conquest_AI_Medium(461, "Conquest (vs AI) (Medium)", false),
    Joust_Practice_Easy(464, "Joust Practice (Easy)", false),
    Clash_Practice_Medium(477, "Clash Practice (Medium)", false),
    Siege_Custom(460, "Siege (Custom)", false),
    Assault_Practice_Medium(480, "Assault Practice (Medium)", false),
    Conquest_Tutorial(463, "Conquest Tutorial", false),
    Clash_Practice_Easy(470, "Clash Practice (Easy)", false),
    Assault_Practice_Easy(479, "Assault Practice (Easy)", false),
    Conquest_Practice_Medium(475, "Conquest Practice (Medium)", false),
    Conquest_Practice_Easy(458, "Conquest Practice (Easy)", false),
    Jungle_Practice(444, "Jungle Practice", false);

    private final int id;
    private final String name;
    private final boolean ranked;
}
