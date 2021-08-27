val ktorVersion: String by project
val logbackVersion: String by project
val koinVersion: String by project

fun DependencyHandler.ktor(module: String, version: String? = ktorVersion): Any =
    "io.ktor:ktor-$module:$version"

plugins {
    application
    kotlin("jvm")
    id("com.bmuschko.docker-java-application")
}

docker {
    javaApplication {
        mainClassName.set(application.mainClass)
        baseImage.set("adoptopenjdk/openjdk11:alpine-jre")
        ports.set(listOf(8080))
        val imageName = project.name
        images.set(
            listOf(
                "$imageName:${project.version}",
                "$imageName:latest"
            )
        )
        jvmArgs.set(listOf("-Xms256m", "-Xmx512m"))
    }
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

    implementation("io.insert-koin:koin-ktor:$koinVersion")
    implementation("io.insert-koin:koin-logger-slf4j:$koinVersion")

    testImplementation(kotlin("test-junit"))
    testImplementation(ktor("server-test-host"))

    implementation(project(":ok-deskshare-be-common"))
    implementation(project(":ok-deskshare-be-dto-rest"))
    implementation(project(":ok-deskshare-be-dto-mapping-rest"))
    implementation(project(":ok-deskshare-be-service"))
    implementation(project(":ok-deskshare-be-stubs"))
}
