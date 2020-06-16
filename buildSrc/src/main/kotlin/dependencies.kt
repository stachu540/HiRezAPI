import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.kotlin

val DependencyHandler.slf4j
    get() = "org.slf4j:slf4j-api:${Version.slf4j}"
val DependencyHandler.`junit-jupiter`
    get() = "org.junit.jupiter:junit-jupiter:${Version.junit5}"
val DependencyHandler.okhttp
    get() = "com.squareup.okhttp3:okhttp:${Version.okhttp}"
val DependencyHandler.`okhttp-logging`
    get() = "com.squareup.okhttp3:logging-interceptor:${Version.okhttp}"
val DependencyHandler.`okhttp-mockwebserver`
    get() = "com.squareup.okhttp3:mockwebserver:${Version.okhttp}"
val DependencyHandler.`rx-java`
    get() = "io.reactivex.rxjava3:rxjava:${Version.`rx-java`}"
val DependencyHandler.lombok
    get() = "org.projectlombok:lombok:${Version.lombok}"
