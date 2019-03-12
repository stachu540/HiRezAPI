package hirez.json

import com.google.gson.annotations.SerializedName
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneOffset
import java.util.*
import java.util.regex.*

private fun parse(timestamp: String) = timestamp.let {
	return@let if (timestamp.contains("\\/")) {
		timestamp.replace("\\/", "/")
	} else timestamp
}.let {
	return@let try {
		SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").apply {
			timeZone = TimeZone.getTimeZone(ZoneOffset.UTC)
		}.parse(it)
	} catch (_: ParseException) {
		Date()
	}
}

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class Ping(
			val rawMessage: String
) : Timestamp {
	val game: String
	val version: String
	val patchVersion: String
	val isPingReceived: Boolean
	override val timestamp: Date
	
	init {
		val matcher = Pattern
					.compile("^(?<game>.+)API \\(ver (?<version>(?:[0-9]+\\.){3}[0-9]+)\\)" + " \\[PATCH - (?<versionName>.+)] - (?<ping>.+)\\. Server Date:(?<timestamp>.+)$")
					.matcher(this.rawMessage)
		matcher.find()
		this.game = matcher.group("game")
		this.version = matcher.group("version")
		this.patchVersion = matcher.group("versionName")
		this.isPingReceived = matcher.group("ping").contains("successful")
		this.timestamp = parse(matcher.group("timestamp"))
	}
	
	override fun toString(): String {
		return rawMessage
	}
}

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class CreateSession(
			@SerializedName("ret_msg")
			val returnedMessage: String,
			@SerializedName("session_id")
			val sessionId: String,
			override val timestamp: Date
) : Timestamp

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class TestSession(
			val rawMessage: String
) : Timestamp {
	val isSuccessful: Boolean
	override val timestamp: Date
	
	init {
		val matcher =
					Pattern.compile("^(.+): developer: ([0-9]{4}) time: " + "(?<timestamp>.+) signature: (.+) session: (.+)$")
								.matcher(rawMessage.replace("\"", ""))
		
		if (matcher.find()) {
			isSuccessful = true
			timestamp = parse(matcher.group("timestamp"))
		} else {
			isSuccessful = false
			timestamp = Date()
		}
	}
}

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class DataUsage(
			val activeSessions: Int,
			val concurrentSessions: Int,
			val requestLimitDaily: Int,
			val sessionCap: Int,
			val sessionTimeLimit: Int,
			val totalRequestsToday: Int,
			val totalSessionsToday: Int
)

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
 */
data class Server(
			val entryDatetime: Date,
			val limitedAccess: Boolean,
			val platform: String,
			val status: Status,
			val version: String
) {
	enum class Status { UP, DOWN }
}