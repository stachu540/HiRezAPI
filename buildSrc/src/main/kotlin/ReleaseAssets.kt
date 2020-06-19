import org.gradle.api.DefaultTask
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.TaskAction
import org.gradle.jvm.tasks.Jar
import java.nio.file.Files

open class ReleaseAssets : DefaultTask() {

    private val github by lazy {
        if (project.githubToken.isNullOrBlank()) org.kohsuke.github.GitHub.connectAnonymously() else org.kohsuke.github.GitHub.connectUsingOAuth(project.githubToken)
    }


    @InputFiles
    var jarFiles: FileCollection = project.files(*project.rootProject.globalProjects
            .mapNotNull { (it.tasks.findByName("shadowJar") as? Jar)?.archiveFile?.get()?.asFile }.toTypedArray())

    @TaskAction
    fun upload() {
        val release = github.getRepository(RootProject.repoSlug).latestRelease

        jarFiles.forEach { file ->
            release.uploadAsset(file, Files.probeContentType(file.toPath()))
        }
    }
}