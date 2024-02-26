import java.net.URI

plugins {
    kotlin("jvm") version "1.9.22"
    application
}

group = "org.gabrielspassos"
version = "1.0-SNAPSHOT"

repositories {
    maven {
        name = "OSSRH"
        url = URI.create("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
        credentials {
            username = System.getenv("MAVEN_USERNAME")
            password = System.getenv("MAVEN_PASSWORD")
        }
    }
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}