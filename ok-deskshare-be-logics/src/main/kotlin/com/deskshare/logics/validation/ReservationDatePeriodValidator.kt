package com.deskshare.logics.validation

import com.deskshare.common.models.ReservationModel
import com.deskshare.validation.ValidationResult
import com.deskshare.validation.ValidatorInterface
import com.deskshare.validation.validator.ValidatorDateAfter

class ReservationDatePeriodValidator: ValidatorInterface<ReservationModel> {
    override fun validate(model: ReservationModel): ValidationResult {
        val dateValidator = ValidatorDateAfter(field = "from", afterDate = model.from)
        return ValidationResult(
            errors = listOf(
                *dateValidator.validate(model.until).errors.toTypedArray()
            )
        )
    }
}
