package com.deskshare.common.models.error

interface ErrorInterface {
    val code: ErrorCode
    val message: String
    val level: ErrorLevel
}
