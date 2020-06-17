import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.TaskContainer
import org.gradle.kotlin.dsl.getByName
import org.kohsuke.github.GitHub
import java.text.SimpleDateFormat
import java.util.*

val Project.github: GitHub
    get() = extensions.getByName("github") as GitHub

fun Project.github(github: GitHub.() -> Unit)=
    extensions.configure<GitHub>("github", github)

val Project.globalProjects
    get() = rootProject.subprojects.filter { it.name !in arrayOf("bom", "all") }

val Project.bintrayUser: String
    get() = System.getenv("BINTRAY_USER") ?: findProperty("bintray.user").toString()

val Project.bintrayApiKey: String
    get() = System.getenv("BINTRAY_API_KEY") ?: findProperty("bintray.api_key").toString()

val Project.githubToken: String?
    get() = System.getenv("GITHUB_TOKEN") ?: findProperty("github.token")?.toString()

val Project.isSnapshot: Boolean
    get() = (rootProject.version as String).endsWith("-SNAPSHOT")

val Project.artifactId: String
    get() = if (name === rootProject.name) rootProject.name else "${rootProject.name}-${name}"

val Project.currentTimestamp: String
    get() = SimpleDateFormat("MMM dd yyyy HH:mm:ss zzz", Locale.ENGLISH).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }.format(Date())

val TaskContainer.deploy
    get() = getByName<DefaultTask>("deploy")