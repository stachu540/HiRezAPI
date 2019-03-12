package hirez.statuspage.json

import hirez.json.Creation
import hirez.json.IdObject
import java.util.*

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 3.0.0
 */
data class IncidentUpdate(
			val body: String,
			override val createdAt: Date,
			val displayAt: Date,
			override val id: String,
			val incidentId: String,
			val status: String,
			override val updatedAt: Date
) : IdObject<String>, Creation, Updates

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 3.0.0
 */
data class Component(
			override val id: String,
			val createdAt: Date,
			val updatedAt: Date,
			val description: Any,
			val name: String,
			val pageId: String,
			val position: Int,
			val status: String
) : IdObject<String>

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 3.0.0
 */
data class ScheduleMaintenance(
			override val createdAt: Date,
			override val id: String,
			val impact: String,
			val incidentUpdates: Array<IncidentUpdate>,
			val monitoringAt: Date?,
			val name: String,
			val pageId: String,
			val resolvedAt: Date?,
			val scheduledFor: String,
			val scheduledUntil: String,
			val shortlink: String,
			val status: String,
			override val updatedAt: Date
) : IdObject<String>, Creation, Updates

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
			override val createdAt: Date,
			override val id: String,
			val impact: String,
			val incidentUpdates: Array<IncidentUpdate>,
			val monitoringAt: Date?,
			val name: String,
			val pageId: String,
			val resolvedAt: Date?,
			val shortlink: String,
			val status: String,
			override val updatedAt: Date
) : IdObject<String>, Creation, Updates

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 3.0.0
 */
data class StatusPage(
			override val id: String,
			val name: String,
			val updatedAt: Date,
			val url: String
) : IdObject<String>