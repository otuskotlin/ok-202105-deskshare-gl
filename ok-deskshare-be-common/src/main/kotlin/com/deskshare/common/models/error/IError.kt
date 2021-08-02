package com.deskshare.common.models.error

interface IError {
    val code: ErrorCode
    val message: String
    val level: ErrorLevel
}

