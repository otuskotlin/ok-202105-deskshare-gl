plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(project(":ok-deskshare-be-common"))
    implementation(project(":ok-deskshare-be-dto-rest"))

    testImplementation(kotlin("test-junit"))
}
