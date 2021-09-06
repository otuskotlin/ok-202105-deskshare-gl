package com.deskshare.ktorapp

import com.deskshare.ktorapp.exception.UnknownEnvironmentException
import io.ktor.application.*

val Application.env: EnvironmentType
    get() {
        val env = environment.config.property("ktor.environment")
        return when (env.getString()) {
            "prod" -> EnvironmentType.Prod
            "test" -> EnvironmentType.Test
            "dev" -> EnvironmentType.Dev
            else -> throw UnknownEnvironmentException()
        }
    }

val Application.isProd get() = env == EnvironmentType.Prod
val Application.isTest get() = env == EnvironmentType.Test
val Application.isDev get() = env == EnvironmentType.Dev
