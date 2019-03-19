package hirez

import io.reactivex.Flowable
import io.reactivex.Single
import java.text.SimpleDateFormat
import java.time.ZoneOffset
import java.util.*

/**
 * Language.
 * @author [Damian Staszewski](damian@stachuofficial.tv)
 * @since 3.0.0
 */
enum class Language(val id: Int) {
	English(1),
	German(2),
	French(3),
	Chinese(5),
	Spanish(7),
	Latin_Spanish(9),
	Portuguese(10),
	Russian(11),
	Polish(12),
	Turkish(13);
	
	override fun toString() = name.replace("_", " ")
}

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 3.0.0
 */
object GitProperties {
	
	val APPLICATION_NAME = "application.name"
	val APPLICATION_VERSION = "application.version"
	val APPLICATION_DESCRIPTION = "application.description"
	val APPLICATION_URL = "application.url"
	val GIT_BRANCH = "git.branch"
	val GIT_COMMIT_ID = "git.commit.id"
	val GIT_COMMIT_ID_ABBREV = "git.commit.id.abbrev"
	val GIT_COMMIT_ID_DESCRIBE = "git.commit.id.describe"
	
	val PROPERTIES = Properties().apply {
		load(GitProperties::class.java.classLoader.getResourceAsStream("git.properties"))
	}
	
	fun get(key: String) =
				PROPERTIES.getProperty(key)
}

fun Calendar.format(format: String) =
			SimpleDateFormat(format).apply {
				timeZone = TimeZone.getTimeZone(ZoneOffset.UTC)
			}.format(time)

fun <T> Single<Array<T>>.flattenArray(): Flowable<T> = flattenAsFlowable { it.asIterable() }
