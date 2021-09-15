package com.deskshare.validation

data class ValidationFieldError(
    override val field: String,
    override val message: String
) : ValidationFieldErrorInterface
