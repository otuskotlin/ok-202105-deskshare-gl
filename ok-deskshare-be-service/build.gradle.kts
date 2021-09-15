plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(project(":ok-deskshare-be-common"))
    implementation(project(":ok-deskshare-be-logics"))
    implementation(project(":ok-deskshare-be-stubs"))
    implementation(project(":ok-deskshare-be-dto-mapping-rest"))
    implementation(project(":ok-deskshare-be-dto-rest"))
}
