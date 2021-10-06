plugins {
    application
    kotlin("jvm")
}

application {
    mainClass.set("com.deskshare.kafkaapp.ApplicationKt")
}

dependencies {
    val logbackVersion: String by project
    val coroutinesVersion: String by project
    val jacksonVersion: String by project

    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

    implementation(project(":ok-deskshare-be-common-kafka"))
    implementation(project(":ok-deskshare-be-common"))
    implementation(project(":ok-deskshare-be-dto-rest"))
    implementation(project(":ok-deskshare-be-dto-mapping-rest"))
    implementation(project(":ok-deskshare-be-service"))
    implementation(project(":ok-deskshare-be-stubs"))

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")
}
