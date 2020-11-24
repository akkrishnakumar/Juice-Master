import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
    application
}

repositories {
    mavenCentral()
}
dependencies {

    testImplementation(kotlin("test-junit5"))
    testImplementation("com.natpryce", "hamkrest", "1.4.2.0")

}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClassName = "MainKt"
}