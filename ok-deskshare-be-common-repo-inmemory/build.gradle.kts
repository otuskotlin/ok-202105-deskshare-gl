plugins {
    kotlin("jvm")
}

dependencies {
    val coroutinesVersion: String by project
    val ehcacheVersion: String by project

    implementation(kotlin("stdlib"))

    implementation(project(":ok-deskshare-be-common"))
    implementation(project(":ok-deskshare-be-common-repo-test"))
    implementation("org.ehcache:ehcache:$ehcacheVersion")

    api(kotlin("test-junit"))
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
}
