import org.gradle.api.DefaultTask
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.TaskAction
import org.gradle.jvm.tasks.Jar
import java.nio.file.Files

open class ReleaseAssets : DefaultTask() {

    @InputFiles
    var jarFiles: FileCollection = project.files(*project.rootProject.globalProjects
            .mapNotNull { (it.tasks.findByName("shadowJar") as? Jar)?.archiveFile?.get()?.asFile }.toTypedArray())

    @TaskAction
    fun upload() {
        val release = project.github.getRepository(RootProject.repoSlug)
                .getReleaseByTagName("${project.version.let { if ("$it".startsWith("v")) it else "v$it" }}")

        jarFiles.forEach { file ->
            release.uploadAsset(file, Files.probeContentType(file.toPath()))
        }
    }
}