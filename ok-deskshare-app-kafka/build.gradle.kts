plugins {
    application
    kotlin("jvm")
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

dependencies {
    val logbackVersion: String by project

    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    implementation(project(":ok-deskshare-be-common-kafka"))
}
