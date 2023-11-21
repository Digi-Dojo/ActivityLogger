val rabbitmq_version: String by project
val slf4j_version: String by project

plugins {
    id("java")
}

group = "it.unibz.activitylogger.async"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":core"))
    implementation(project(mapOf("path" to ":inferrers")))

    implementation("com.rabbitmq:amqp-client:$rabbitmq_version")
    implementation("org.slf4j:slf4j-api:$slf4j_version")
    implementation("org.slf4j:slf4j-simple:$slf4j_version")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}