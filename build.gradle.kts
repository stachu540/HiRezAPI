import com.jfrog.bintray.gradle.BintrayExtension
import com.jfrog.bintray.gradle.tasks.BintrayPublishTask
import com.jfrog.bintray.gradle.tasks.BintrayUploadTask
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.text.SimpleDateFormat
import java.util.*

plugins {
	`maven-publish`
	kotlin("jvm") version "1.3.21"
	id("com.jfrog.bintray") version "1.8.4"
	id("org.jetbrains.dokka") version "0.9.17"
	id("com.jfrog.artifactory") version "4.9.3"
	id("com.github.ben-manes.versions") version "0.21.0"
	id("com.github.johnrengelman.shadow") version "5.0.0"
	id("com.gorylenko.gradle-git-properties") version "2.0.0"
}

val timestamp = SimpleDateFormat("MMM dd yyyy HH:mm:ss zzz", Locale.ENGLISH).apply {
	timeZone = TimeZone.getTimeZone("GMT")
}.format(Date())

val bintrayUser = System.getenv("BINTRAY_USER") ?: findProperty("bintray.user").toString()
val bintrayApiKey = System.getenv("BINTRAY_API_KEY") ?: findProperty("bintray.api_key").toString()
val sonatypeUser = System.getenv("CENTRAL_USER") ?: findProject("central.user").toString()
val sonatypePassword = System.getenv("CENTRAL_PASSWORD") ?: findProject("central.password").toString()

allprojects {
	repositories {
		jcenter()
	}
}

subprojects {
	apply(plugin = "maven-publish")
	apply(plugin = "com.jfrog.bintray")
	apply(plugin = "org.jetbrains.dokka")
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "com.gorylenko.gradle-git-properties")
	
	val emptySource = sourceSets.main.get().allSource.isEmpty && !arrayOf("bom", "all").contains(project.name)
	
	base.archivesBaseName = "hirez-${project.projectDir.canonicalPath
				.replace(rootProject.projectDir.canonicalPath, "")
				.substring(1).replace('\\', '-')}"
	
	val dokka: DokkaTask by tasks.named("dokka", DokkaTask::class) {
		moduleName = base.archivesBaseName
		jdkVersion = 8
	}
	
	val kdocJar by tasks.registering(Jar::class) {
		from(dokka.outputDirectory)
		classifier = "kdoc"
	}
	
	val sourceJar by tasks.registering(Jar::class) {
		from(sourceSets.main.get().allSource)
		classifier = "sources"
	}
	
	dependencies {
		if (!arrayOf("bom", "all").contains(project.name)) {
			compile(kotlin("stdlib-jdk8"))
			
			// https://docs.gradle.org/5.0/userguide/managing_transitive_dependencies.html#sec:bom_import
			compile(enforcedPlatform("com.fasterxml.jackson:jackson-bom:2.9.8"))
			
			compile("org.slf4j:slf4j-api:1.7.25")
			
			testCompile("ch.qos.logback:logback-classic:1.2.3")
		}
	}
	
	tasks {
		withType<KotlinCompile> {
			incremental = true
			kotlinOptions.jvmTarget = "1.8"
		}
		
		withType<Jar> {
			manifest {
				attributes(
							"Manifest-Version" to "1.0",
							"Created-By" to "Gradle ${gradle.gradleVersion} - JDK ${System.getProperty("java.specification.version")} (${System.getProperty(
										"java.version"
							)})",
							"Implementation-Title" to rootProject.name,
							"Implementation-Vendor" to base.archivesBaseName,
							"Implementation-Version" to version,
							"Implementation-Date" to timestamp
				)
			}
		}
		
		val dependencySize by creating {
			if (arrayOf("bom", "auth").contains(project.name)) {
				enabled = false
			}
			doLast {
				var size = 0.0
				val formatStr = "%,10.2f"
				configurations["default"].map { it.length().toDouble() / (1024 * 1024) }.forEach { size += it }
				
				val out = StringBuffer()
							.append("Total dependencies size:".padEnd(45))
							.append("${String.format(formatStr, size)} MiB\n\n")
				
				configurations["default"]
							.sortedBy { -it.length() }
							.forEach {
								out.append(it.name.padEnd(45))
											.append("${String.format(formatStr, (it.length().toDouble() / 1024))} KiB\n")
							}
				println(out)
			}
		}
		
		withType<BintrayUploadTask> {
			if (emptySource) {
				enabled = false
			}
		}
		
		withType<BintrayPublishTask> {
			if (emptySource) {
				enabled = false
			}
		}
		
		withType<Test> {
			shouldRunAfter(dependencySize)
		}
	}
	
	gitProperties {
		keys = listOf(
					"git.branch",
					"git.commit.id",
					"git.commit.id.abbrev",
					"git.commit.id.describe"
		)
		dateFormatTimeZone = "GMT"
		customProperty("application.name", rootProject.name)
		customProperty("application.version", rootProject.version)
		customProperty("application.description", rootProject.description)
	}
	
	publishing {
		publications {
			register<MavenPublication>("maven") {
				artifactId = base.archivesBaseName
				if (project.name != "bom") {
					if (project.name != "all") {
						from(components["java"])
						artifact(sourceJar.get())
					}
					artifact(kdocJar.get())
				}
				pom {
					url.set("https://github.com/stachu540/HiRezAPI")
					issueManagement {
						system.set("GitHub")
						url.set("https://github.com/stachu540/HiRezAPI/issues")
					}
					ciManagement {
						system.set("Travis-CI")
						url.set("https://travis-ci.org/stachu540/HiRezAPI")
					}
					inceptionYear.set("2019")
					developers {
						developer {
							id.set("stachu540")
							name.set("Damian Staszewski")
							url.set("https://github.com/stachu540")
							timezone.set("Europe/Warsaw")
						}
					}
					licenses {
						license {
							name.set("MIT")
							url.set("https://github.com/stachu540/HiRezAPI/blob/master/LICENCE.md")
							distribution.set("repo")
						}
					}
					scm {
						connection.set("scm:git:https://github.com/stachu540/HiRezAPI.git")
						developerConnection.set("scm:git:git@github.com:stachu540/HiRezAPI.git")
						url.set("https://github.com/stachu540/HiRezAPI")
					}
					distributionManagement {
						downloadUrl.set("https://github.com/stachu540/HiRezAPI/releases")
					}
				}
			}
		}
	}
	
	bintray {
		user = bintrayUser
		key = bintrayApiKey
		dryRun = false
		publish = true
		override = false
		setPublications("maven")
		pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
			userOrg = bintrayUser
			repo = "Java"
			name = "HiRezAPI"
			desc = "Java-Based API Wrapper for Hi-Rez Studios games."
			setLicenses("MIT")
			publicDownloadNumbers = true
			vcsUrl = "https://github.com/stachu540/HiRezAPI.git"
			version(delegateClosureOf<BintrayExtension.VersionConfig> {
				name = rootProject.version.toString()
				vcsTag = "v${rootProject.version}"
				released = Date().toString()
			})
		})
	}
}

rootProject.tasks {
	withType<BintrayUploadTask> {
		enabled = false
	}
	withType<BintrayPublishTask> {
		enabled = false
	}
}

tasks.withType<Wrapper> {
	gradleVersion = "5.2.1"
	distributionType = Wrapper.DistributionType.ALL
}