package hirez.enums

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
enum class Portal(val id: Int) {
	UNKNOWN(-1),
	HIREZ(1),
	STEAM(5),
	PS4(9),
	XBOX(10),
	SWITCH(22)
}