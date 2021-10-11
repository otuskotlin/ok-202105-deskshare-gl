plugins {
    kotlin("jvm")
}

dependencies {
    val coroutinesVersion: String by project
    val kafkaVersion: String by project

    implementation("org.apache.kafka:kafka-clients:$kafkaVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation(kotlin("stdlib"))

    testImplementation(kotlin("test-junit"))


    implementation(kotlin("stdlib"))

    val jacksonVersion: String by project
    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    testImplementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")
}
