package hirez

import java.util.*

/**
 *
 * @author Damian Staszewski [damian@stachuofficial.tv]
 * @version %I%, %G%
 * @since 1.0
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