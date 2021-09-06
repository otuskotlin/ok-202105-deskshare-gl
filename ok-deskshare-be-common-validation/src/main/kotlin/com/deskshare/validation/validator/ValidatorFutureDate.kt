package com.deskshare.validation.validator

import com.deskshare.validation.ValidationResult
import com.deskshare.validation.ValidatorInterface
import java.time.LocalDateTime

class ValidatorFutureDate(
    private val field: String = ""
) : ValidatorInterface<LocalDateTime> {
    override fun validate(value: LocalDateTime): ValidationResult =
        ValidatorDateAfter(
            afterDate = LocalDateTime.now(),
            field = field
        ).validate(value)
}
