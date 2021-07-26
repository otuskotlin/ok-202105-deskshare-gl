plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))

    testImplementation(kotlin("test-junit5"))

    val kotestVersion: String by project
    val mockitoVersion: String by project
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("org.mockito.kotlin:mockito-kotlin:$mockitoVersion")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
