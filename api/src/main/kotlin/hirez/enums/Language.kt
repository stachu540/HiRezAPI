package hirez.enums

/**
 * Language.
 * @author [Damian Staszewski](damian@stachuofficial.tv)
 * @since 1.8
 */
enum class Language(val id: Int) {
	English(1),
	German(2),
	French(3),
	Spanish(7),
	Latin_Spanish(9),
	Portuguese(10),
	Russian(11),
	Polish(12),
	Turkish(13);
	
	internal val value = name.replace("_", " ")
	
	override fun toString() = value
}
