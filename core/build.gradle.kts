plugins {
    id("java")
}

group = "it.unibz.activitylogger.core"
version = "1.0-SNAPSHOT"

java {
    modularity.inferModulePath = true
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}