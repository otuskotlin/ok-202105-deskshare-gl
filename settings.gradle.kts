rootProject.name = "deskshare"

pluginManagement {
    plugins {
        val kotlinVersion: String by settings
        val openApiVersion: String by settings
        val bmuschkoVersion: String by settings

        kotlin("jvm") version kotlinVersion
        kotlin("multiplatform") version kotlinVersion
        id("org.openapi.generator") version openApiVersion
        id("com.bmuschko.docker-java-application") version bmuschkoVersion
    }
}

include("ok-m1l1")
include("ok-m1l4")
include("ok-m1l5")
include("ok-m1l6")
include("ok-m2l2-testing")
include("ok-deskshare-be-common")
include("ok-deskshare-be-dto-rest")
include("ok-deskshare-be-dto-mapping-rest")
include("ok-deskshare-be-common-cor")
include("ok-deskshare-be-common-validation")
include("ok-deskshare-app-ktor")
include("ok-deskshare-be-stubs")
include("ok-deskshare-be-service")
