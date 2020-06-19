import java.util.*

plugins {
    java
    `maven-publish`
    id("com.jfrog.bintray") version Version.bintray
    id("io.freefair.lombok") version Version.freefairPlugin
    id("com.github.johnrengelman.shadow") version Version.shadow
}

allprojects {

    if ("$version".startsWith("v")) {
        version = "$version".substring(1)
    }

    repositories {
        jcenter()
    }

    pluginManager.withPlugin("io.freefair.lombok") {
        lombok {
            config.put("lombok.anyConstructor.addConstructorProperties", "true")
        }
    }

    pluginManager.withPlugin("com.jfrog.bintray") {
        bintray {
            user = bintrayUser
            key = bintrayApiKey
            setPublications("maven")
            pkg.apply {
                userOrg = bintrayUser
                repo = "Java"
                name = "HiRezAPI"
                desc = rootProject.description
                setLicenses("MIT")
                publicDownloadNumbers = true
                vcsUrl = "${RootProject.githubUrl}.git"
                version.apply {
                    name = "${project.version}"
                    vcsTag = "v${project.version}"
                    released = "${Date()}"
                }
            }
        }
    }
}

subprojects {
    apply(plugin = "maven-publish")
    apply(plugin = "com.jfrog.bintray")

    if (name != "bom") {
        apply(plugin = "java")
        apply(plugin = "io.freefair.lombok")
        apply(plugin = "com.github.johnrengelman.shadow")

        java {
            if (name != "all") {
                withSourcesJar()
            }
            withJavadocJar()
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        base {
            archivesBaseName = artifactId
        }

        dependencies {
            if (name != "all") {
                compileOnly(lombok)
                annotationProcessor(lombok)

                compile(slf4j)
                compile("com.google.code.findbugs:jsr305:3.0.2")

                testImplementation("ch.qos.logback:logback-classic:${Version.logback}")
            }
        }

        publishing.publications.withType<MavenPublication> {
            from(components["java"])
        }

        tasks {
            withType<JavaCompile> {
                options.apply {
                    isIncremental = true
                    encoding = "UTF-8"
                }
            }
            jar {
                manifest {
                    attributes(
                            "Manifest-Version" to "1.0",
                            "Created-By" to "Gradle ${gradle.gradleVersion} - JDK ${System.getProperty("java.specification.version")} (${System.getProperty("java.version")})",
                            "Implementation-Title" to rootProject.name,
                            "Implementation-Vendor" to base.archivesBaseName,
                            "Implementation-Version" to project.version,
                            "Implementation-Date" to project.currentTimestamp
                    )
                }
            }

            shadowJar {
                isEnabled = name != "api"
                archiveClassifier.set("shaded")
            }

            withType<Javadoc> {
                options {
                    encoding = "UTF-8"
                    if (JavaVersion.current().isJava9Compatible) {
                        (this as StandardJavadocDocletOptions).addBooleanOption("-no-module-directories", true)
                    }
                }
            }
        }
    }

    publishing {
        publications {
            create<MavenPublication>("maven") {
                artifactId = project.artifactId
                pom {
                    default()
                }
            }
        }
    }
}

tasks {
    wrapper {
        gradleVersion = "6.5"
        distributionType = Wrapper.DistributionType.ALL
    }
    create<ReleaseAssets>("releaseAssets") {
        dependsOn(shadowJar)
        onlyIf { !githubToken.isNullOrBlank() }
        jarFiles = files(*globalProjects.mapNotNull {
            if (it.name == "api") null
            else (it.tasks.findByName("shadowJar") as? org.gradle.jvm.tasks.Jar)?.archiveFile?.get()?.asFile
        }.toTypedArray())
    }
}