package hirez.statuspage.json

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class Incidents(
			override val page: StatusPage,
			override val incidents: Array<Incident>
) : IStatusPage, IIncidents