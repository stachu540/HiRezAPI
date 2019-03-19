package hirez.json.statuspage

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class Components(
			val page: StatusPage,
			val components: List<Component>
)

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class Incidents(
			val page: StatusPage,
			val incidents: List<Incident>
)

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class ScheduledMaintenances(
			val page: StatusPage,
			val scheduledMaintenances: List<ScheduleMaintenance>
)

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class StatusData(
			val page: StatusPage,
			val status: StatusData
)

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class Summary(
			val status: StatusData,
			val components: List<Component>,
			val incidents: List<Incident>,
			val scheduledMaintenances: List<ScheduleMaintenance>,
			val page: StatusPage
)