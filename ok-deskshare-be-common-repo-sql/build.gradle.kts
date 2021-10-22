plugins {
    kotlin("jvm")
}

dependencies {
    val coroutinesVersion: String by project
    val exposedVersion: String by project
    val postgresDriverVersion: String by project

    implementation(kotlin("stdlib"))

    implementation(project(":ok-deskshare-be-common"))
    implementation(project(":ok-deskshare-be-common-repo-test"))

    implementation("org.postgresql:postgresql:$postgresDriverVersion")

    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")

    api(kotlin("test-junit"))
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
}
