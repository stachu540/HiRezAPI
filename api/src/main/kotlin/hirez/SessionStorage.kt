package hirez

import hirez.json.CreateSession

interface SessionStorage {
	fun set(session: CreateSession)
	
	fun get(): String?
	
	fun remove()
	
	val isPresent: Boolean
	
	companion object {
		val DEFAULT = object : SessionStorage {
			private var key: String? = null
			
			override fun set(session: CreateSession) {
				this.key = session.sessionId
			}
			
			override fun get(): String? = key
			
			override fun remove() {
				key = null
			}
			
			override val isPresent = key != null
		}
	}
}
