plugins {
    application
    id("java")
}

group = "it.unibz.activitylogger.main"
version = "1.0-SNAPSHOT"

application {
    mainClass = "it.unibz.activitylogger.main.ActivityLoggerMain"
    mainModule = "it.unibz.activitylogger.main"
}

java {
    modularity.inferModulePath = true
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":core"))
    implementation(project(mapOf("path" to ":inferrers")))
    implementation(project(mapOf("path" to ":http")))
    implementation(project(mapOf("path" to ":async")))
}
