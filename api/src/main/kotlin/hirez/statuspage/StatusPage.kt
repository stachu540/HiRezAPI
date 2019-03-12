package hirez.statuspage

import com.google.gson.Gson
import hirez.Http
import hirez.statuspage.json.Components
import hirez.statuspage.json.Incidents
import hirez.statuspage.json.ScheduledMaintenances
import hirez.statuspage.json.StatusData
import hirez.statuspage.json.Summary

/**
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
class StatusPage(gson: Gson, userAgent: String) {
	private val http = Http(gson, userAgent)
	
	private fun combineUrl(endpoint: String): String = "http://stk4xr7r1y0r.statuspage.io/api/v2$endpoint"
	
	val summary
		get() = http.call<Summary>(combineUrl("/summary.json"))
	
	
	val status
		get() = http.call<StatusData>(combineUrl("/status.json"))
	
	val components
		get() = http.call<Components>(combineUrl("/components.json"))
	
	val unresolvedIncidents
		get() = http.call<Incidents>(combineUrl("/incidents/unresolved.json"))
	val incidents
		get() = http.call<Incidents>(combineUrl("/incidents.json"))
	
	val upcomingScheduledMaintenances
		get() = http.call<ScheduledMaintenances>(combineUrl("/scheduled-maintenances/upcoming.json"))
	val activeScheduledMaintenances
		get() = http.call<ScheduledMaintenances>(combineUrl("/scheduled-maintenances/active.json"))
	val allScheduledMaintenances
		get() = http.call<ScheduledMaintenances>(combineUrl("/scheduled-maintenances.json"))
}
