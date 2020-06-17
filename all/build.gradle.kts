dependencies {
    rootProject.globalProjects.forEach {
        compile(it)
    }
}

tasks {
    javadoc {
        isFailOnError = false
        title = "${rootProject.name} ${rootProject.version} API"
        options {
            windowTitle = "${rootProject.name} ${rootProject.version}"
            encoding = "UTF-8"
            (this as StandardJavadocDocletOptions).apply {
                isAuthor = true
                quiet()
                if (JavaVersion.current().isJava9Compatible) {
                    addBooleanOption("-no-module-directories", true)
                }
            }
        }

        rootProject.globalProjects.forEach {
            source += it.tasks.javadoc.get().source
            classpath += it.tasks.javadoc.get().classpath
            excludes += it.tasks.javadoc.get().excludes
            includes += it.tasks.javadoc.get().includes
        }
    }
}