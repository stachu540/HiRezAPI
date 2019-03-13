package hirez.smite

import hirez.Queue

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
enum class GameQueue(
			override val id: Int,
			override val value: String
) : Queue {
	ARENA(435, "Arena"),
	JOUST(448, "Joust"),
	CONQUEST(426, "Conquest"),
	ASSAULT(445, "Assault"),
	CLASH(466, "Clash"),
	CONQUEST_LEAGUE(451, "Conquest League"),
	JOUST_LEAGUE(450, "Joust League"),
	MOTD(434, "Match of the Day"),
	JOUST_CUSTOM(441, "Joust (Custom)"),
	SIEGE(459, "Siege"),
	DUEL(440, "Duel"),
	ARENA_AI_MEDIUM(468, "Arena AI (Medium)"),
	JOUST_AI_MEDIUM(456, "Joust AI (Medium)"),
	ARENA_TUTORIAL(462, "Arena Tutorial"),
	ARENA_CUSTOM(438, "Arena (Custom)"),
	CONQUEST_CUSTOM(429, "Conquest (Custom)"),
	CONQUEST_AI_MEDIUM(461, "Conquest AI (Medium)"),
	ARENA_AI_EASY(457, "Arena AI (Easy)"),
	CONQUEST_AI_EASY(476, "Conquest AI (Easy)"),
	JOUST_AI_EASY(474, "Joust AI (Easy)"),
	CONQUEST_LEAGUE_CONTROLLER(504, "Conquest League (Controller)"),
	ARENA_PRACTICE_MEDIUM(472, "Arena Practice (Medium)"),
	JOUST_LEAGUE_CONTROLLER(503, "Joust League (Controller)"),
	ASSAULT_CUSTOM(446, "Assault (Custom)"),
	ASSAULT_AI_MEDIUM(454, "Assault AI (Medium)"),
	JOUST_PRACTICE_MEDIUM(473, "Joust Practice (Medium)"),
	ARENA_PRACTICE_EASY(443, "Arena Practice (Easy)"),
	CLASH_CUSTOM(467, "Clash (Custom)"),
	CLASH_AI_MEDIUM(469, "Clash AI (Medium)"),
	ASSAULT_AI_EASY(481, "Assault AI (Easy)"),
	SIEGE_CUSTOM(460, "Siege (Custom)"),
	CONQUEST_PRACTICE_MEDIUM(475, "Conquest Practice (Medium)"),
	JOUST_PRACTICE_EASY(464, "Joust Practice (Easy)"),
	DUEL_CONTROLLER(502, "Duel (Controller)"),
	CONQUEST_PRACTICE_EASY(458, "Conquest Practice (Easy)"),
	CLASH_AI_EASY(478, "Clash AI (Easy)"),
	ASSAULT_PRACTICE_MEDIUM(480, "Assault Practice (Medium)"),
	ASSAULT_PRACTICE_EASY(479, "Assault Practice (Easy)"),
	CLASH_PRACTICE_MEDIUM(477, "Clash Practice (Medium)"),
	CLASH_PRACTICE_EASY(470, "Clash Practice (Easy)"),
	CLASH_TUTORIAL(471, "Clash Tutorial"),
	BASIC_TUTORIAL(436, "Basic Tutorial");
	
	override val isRanked: Boolean = rankedQueues.contains(id)
	
}