rootProject.name = "deskshare"

pluginManagement {
    plugins {
        val kotlinVersion: String by settings
        kotlin("jvm") version kotlinVersion
        kotlin("multiplatform") version kotlinVersion
    }
}

include("ok-m1l1")
include("ok-m1l4")
include("ok-m1l5")
include("ok-m1l6")
include("ok-m2l2-testing")
include("ok-deskshare-be-common")
include("ok-deskshare-be-dto-rest")
