import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
    application
}

repositories {
    mavenCentral()
}
dependencies {

    implementation("org.jetbrains.kotlin", "kotlin-reflect", "1.4.10")
    implementation("com.fasterxml.jackson.module", "jackson-module-kotlin", "2.9.8")

    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter", "junit-jupiter-params", "5.0.0")
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