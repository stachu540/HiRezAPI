plugins {
    id("com.gorylenko.gradle-git-properties") version Version.`gradle-git`
}

dependencies {
    compile("com.fasterxml.jackson.core:jackson-annotations:${Version.jackson}")

    compile("com.fasterxml.jackson.core:jackson-databind:${Version.jackson}")
    compile("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${Version.jackson}")
    compile("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:${Version.jackson}")

    compile(`rx-java`)

    compile(okhttp)
    compile(`okhttp-logging`)

    testImplementation(`okhttp-mockwebserver`)
}

gitProperties {
    failOnNoGitDirectory = false
    keys = listOf(
            "git.branch",
            "git.commit.id",
            "git.commit.id.abbrev",
            "git.commit.id.describe"
    )
    dateFormatTimeZone = "GMT"
    customProperty("application.name", rootProject.name)
    customProperty("application.version", rootProject.version)
    customProperty("application.url", RootProject.githubUrl)
    customProperty("application.description", rootProject.description)
}