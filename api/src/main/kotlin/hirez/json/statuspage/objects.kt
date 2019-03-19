package hirez.json.statuspage

import java.util.*

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 3.0.0
 */
data class IncidentUpdate(
			val body: String,
			val createdAt: Date,
			val displayAt: Date,
			val id: String,
			val incidentId: String,
			val status: String,
			val updatedAt: Date
)

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 3.0.0
 */
data class Component(
			val id: String,
			val createdAt: Date,
			val updatedAt: Date,
			val description: Any,
			val name: String,
			val pageId: String,
			val position: Int,
			val status: String
)

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 3.0.0
 */
data class ScheduleMaintenance(
			val createdAt: Date,
			val id: String,
			val impact: String,
			val incidentUpdates: List<IncidentUpdate>,
			val monitoringAt: Date?,
			val name: String,
			val pageId: String,
			val resolvedAt: Date?,
			val scheduledFor: String,
			val scheduledUntil: String,
			val shortlink: String,
			val status: String,
			val updatedAt: Date
)

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 3.0.0
 */
data class Status(
			val description: String,
			val indicator: String
)

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 3.0.0
 */
data class Incident(
			val createdAt: Date,
			val id: String,
			val impact: String,
			val incidentUpdates: List<IncidentUpdate>,
			val monitoringAt: Date?,
			val name: String,
			val pageId: String,
			val resolvedAt: Date?,
			val shortlink: String,
			val status: String,
			val updatedAt: Date
)

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 3.0.0
 */
data class StatusPage(
			val id: String,
			val name: String,
			val updatedAt: Date,
			val url: String
)