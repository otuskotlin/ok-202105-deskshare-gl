plugins {
    kotlin("jvm")
}

dependencies {
    val coroutinesVersion: String by project

    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test-junit"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

    implementation(project(":ok-deskshare-be-common"))
    implementation(project(":ok-deskshare-be-common-cor"))
    implementation(project(":ok-deskshare-be-common-validation"))
    implementation(project(":ok-deskshare-be-stubs"))

}
