plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))

    val kotestVersion: String by project
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
