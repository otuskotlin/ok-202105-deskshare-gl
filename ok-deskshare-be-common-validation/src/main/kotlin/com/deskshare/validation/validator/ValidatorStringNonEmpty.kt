package com.deskshare.validation.validator

import com.deskshare.validation.ValidationFieldError
import com.deskshare.validation.ValidationResult
import com.deskshare.validation.ValidatorInterface

class ValidatorStringNonEmpty(
    private val field: String = "",
    private val message: String = "String must not be empty"
) : ValidatorInterface<String?> {
    override fun validate(value: String?): ValidationResult {
        return if (value.isNullOrBlank()) {
            ValidationResult.fromError(
                ValidationFieldError(
                    field = field,
                    message = message
                )
            )
        } else {
            ValidationResult.SUCCESS
        }
    }
}
