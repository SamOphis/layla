import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    id("application")
}

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    implementation(kotlin("stdlib")) // Kotlin stdlib.
    implementation("dev.kord:kord-core:0.8.0-M15") // Discord API client
    implementation("ch.qos.logback:logback-classic:1.3.0-alpha16") // SLF4J frontend
    implementation("io.insert-koin:koin-core:3.2.0") // Dependency injection framework.
}

application {
    mainClass.set("uk.luau.layla.startup.MainKt")
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"