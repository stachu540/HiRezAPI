package hirez.statuspage.json

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class ScheduledMaintenances(
			override val page: StatusPage,
			override val scheduledMaintenances: Array<ScheduleMaintenance>
) : IStatusPage, IScheduledMaintenances