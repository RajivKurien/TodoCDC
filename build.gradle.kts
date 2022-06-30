import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    kotlin("plugin.serialization") version "1.6.21"
    application
}

group = "me.raku"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


val ktor_version = "2.0.3"

dependencies {

    // HTTP Client
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")

    // JSON serialization/deserialization
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    testImplementation(platform("org.junit:junit-bom:5.8.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation("au.com.dius.pact.consumer:junit5:4.2.21")


    implementation("org.apache.httpcomponents.client5:httpclient5-fluent:5.1.3")



    testImplementation("io.ktor:ktor-client-mock:2.0.3")
    testImplementation("org.assertj:assertj-core:3.23.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}