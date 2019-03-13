import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("io.spring.dependency-management") version "1.0.7.RELEASE"
}

description = "Bills of Materials (BOM) for HiRezAPI packages"

tasks {
	withType<KotlinCompile> {
		enabled = false
	}
	withType<DokkaTask> {
		enabled = false
	}
	withType<Jar> {
		enabled = false
	}
	withType<Test> {
		enabled = false
	}
}

extensions.configure(DependencyManagementExtension::class) {
	dependencies {
		rootProject.subprojects.forEach {
			if (!arrayOf("bom", "all", "api").contains(it.name)) {
				dependency("${it.group}:${it.base.archivesBaseName}:${it.version}")
			}
		}
	}
}