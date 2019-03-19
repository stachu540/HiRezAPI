package hirez.realm

import hirez.api.Queue
import hirez.json.IdObject

enum class RankCriteria(override val id: Int) : IdObject<Int> {
	TEAM_WINS(1),
	TEAM_AVERAGE_PLACEMENT(2),
	INDIVIDUAL_AVERAGE_KILLS(3),
	WIN_RATE(4)
}

enum class Queues(
			override val id: Int
) : Queue {
	SOLO(474),
	DUO(475),
	QUAD(476);
	
	override val value: String = name.capitalize()
	
	override val isRanked = false
	
	companion object {
		fun from(id: Int) = values().first { it.id == id }
	}
}