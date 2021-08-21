val ktorVersion: String by project
val logbackVersion: String by project

fun DependencyHandler.ktor(module: String, version: String? = ktorVersion): Any =
    "io.ktor:ktor-$module:$version"

plugins {
    application
    kotlin("jvm")
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    implementation(ktor("server-core"))
    implementation(ktor("server-netty"))
    implementation(ktor("jackson"))

    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")

    testImplementation(kotlin("test-junit"))
    testImplementation(ktor("server-test-host"))

    implementation(project(":ok-deskshare-be-common"))
    implementation(project(":ok-deskshare-be-dto-rest"))
}
