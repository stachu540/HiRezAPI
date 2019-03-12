package hirez.statuspage.json

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class Components(
			override val page: StatusPage,
			override val components: Array<Component>
) : IStatusPage, IComponents