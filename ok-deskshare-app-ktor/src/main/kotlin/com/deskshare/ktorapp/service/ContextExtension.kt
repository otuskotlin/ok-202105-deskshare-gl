package com.deskshare.ktorapp.service

import com.deskshare.common.context.Context
import com.deskshare.common.models.error.ErrorCode
import io.ktor.http.*

fun Context.toHttpStatusCode(): HttpStatusCode {
    return if (!isFailed()) {
        HttpStatusCode.OK
    } else {
        error?.let {
            when (error!!.code) {
                ErrorCode.NotFound -> HttpStatusCode.NotFound
                ErrorCode.Validation -> HttpStatusCode.UnprocessableEntity
                ErrorCode.Authentication -> HttpStatusCode.Unauthorized
                ErrorCode.Runtime, ErrorCode.Logic -> HttpStatusCode.InternalServerError
            }
        } ?: HttpStatusCode.InternalServerError
    }
}
