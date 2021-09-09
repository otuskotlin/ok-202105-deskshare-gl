package com.deskshare.logics.validation

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.command.CreateCommandRequest
import com.deskshare.validation.ValidationResult
import com.deskshare.validation.ValidatorInterface
import com.deskshare.validation.validator.ValidatorDateAfter

class ReservationDatePeriodValidator: ValidatorInterface<RequestContext<CreateCommandRequest>> {
    override fun validate(ctx: RequestContext<CreateCommandRequest>): ValidationResult {
        val dateValidator = ValidatorDateAfter(field = "from", afterDate = ctx.request.responseModel.from)
        return ValidationResult(
            errors = listOf(
                *dateValidator.validate(ctx.request.requestModel.until).errors.toTypedArray()
            )
        )
    }
}
