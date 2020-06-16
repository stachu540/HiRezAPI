import org.gradle.api.publish.maven.MavenPom
import org.gradle.api.publish.maven.MavenPomDeveloperSpec
import org.gradle.api.publish.maven.MavenPomLicenseSpec
import java.util.*

fun MavenPom.default() {
    url.set(RootProject.webUrl)
    issueManagement {
        system.set("GitHub")
        url.set(RootProject.issues)
    }
    ciManagement {
        system.set("GitHub Actions")
        url.set(RootProject.ciUrl)
    }
    inceptionYear.set(Calendar.getInstance().get(Calendar.YEAR).toString())
    developers { all }
    licenses { mit("repo", RootProject.mitLicense) }
    scm {
        connection.set(RootProject.scmHttps)
        developerConnection.set(RootProject.scmSsh)
        url.set(RootProject.githubUrl)
    }
    distributionManagement { downloadUrl.set(RootProject.dlUrl) }
}

fun MavenPomLicenseSpec.mit(distribution: String, url: String) {
    license {
        name.set("MIT Licence")
        this.distribution.set(distribution)
        this.url.set(url)
    }
}

val MavenPomDeveloperSpec.all: Unit
    get() {
        stachu540
    }

val MavenPomDeveloperSpec.stachu540
    get() = developer {
        name.set("Damian Staszewski")
        url.set("https://github.com/stachu540")
        timezone.set("Europe/Warsaw")
        roles.addAll("creator", "developer", "owner")
    }