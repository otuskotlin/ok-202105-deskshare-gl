package com.deskshare.validation.validator

import com.deskshare.validation.ValidationFieldError
import com.deskshare.validation.ValidationResult
import com.deskshare.validation.ValidatorInterface
import java.time.LocalDateTime

class ValidatorDateAfter(
    private val afterDate: LocalDateTime,
    private val field: String = ""
) : ValidatorInterface<LocalDateTime> {
    override fun validate(value: LocalDateTime): ValidationResult {
        return if (!value.isAfter(afterDate)) {
            ValidationResult.fromError(
                ValidationFieldError(
                    field = field,
                    message = "Date ${value.toString()} must be after ${afterDate.toString()} date"
                )
            )
        } else {
            ValidationResult.SUCCESS
        }
    }
}
