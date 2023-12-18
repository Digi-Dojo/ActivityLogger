plugins {
    id("java")
}

group = "it.unibz.activitylogger.persistence"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    modularity.inferModulePath = true
}

dependencies {
    implementation(project(mapOf("path" to ":core")))
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}