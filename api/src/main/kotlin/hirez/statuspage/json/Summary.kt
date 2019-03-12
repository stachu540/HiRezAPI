package hirez.statuspage.json

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class Summary(
			override val status: StatusData,
			override val components: Array<Component>,
			override val incidents: Array<Incident>,
			override val scheduledMaintenances: Array<ScheduleMaintenance>,
			override val page: StatusPage
) : IStatusPage, IStatus,
			IComponents, IIncidents,
			IScheduledMaintenances