import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktor_version = "2.0.3"
val logback_version = "1.2.11"

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



dependencies {

    // HTTP Client
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")

    // JSON serialization/deserialization
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    // Server
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")


    /*
     ~~~~~~~~~~~~   Test libraries   ~~~~~~~~~~~~
     */

    testImplementation(platform("org.junit:junit-bom:5.8.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.23.1")

    testImplementation("au.com.dius.pact.consumer:junit5:4.2.21")
    testImplementation("io.ktor:ktor-client-mock:2.0.3")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.5.10")
    testImplementation("io.ktor:ktor-server-test-host:$ktor_version")

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