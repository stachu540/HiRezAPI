plugins {
    `java-platform`
}

dependencies.constraints {
    rootProject.globalProjects.forEach {
        it.pluginManager.withPlugin("maven-publish") {
            it.publishing.publications.withType<MavenPublication> {
                api("$groupId:$artifactId:$version")
            }
        }
    }
}

publishing.publications.withType<MavenPublication> {
    from(components["javaPlatform"])
}

