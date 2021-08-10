group = "com.deskshare"
version = "0.0.1"

plugins {
    kotlin("jvm") apply false
    kotlin("multiplatform") apply false
}

subprojects {
    group = rootProject.group
    version = rootProject.version

    repositories {
        mavenCentral()
    }
}
