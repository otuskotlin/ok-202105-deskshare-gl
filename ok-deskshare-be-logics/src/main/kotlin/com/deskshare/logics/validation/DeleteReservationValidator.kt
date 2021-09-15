package com.deskshare.logics.validation

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.command.DeleteCommandRequest
import com.deskshare.common.context.command.UpdateCommandRequest
import com.deskshare.validation.ValidationResult
import com.deskshare.validation.ValidatorInterface
import com.deskshare.validation.validator.ValidatorStringNonEmpty

class DeleteReservationValidator: ValidatorInterface<RequestContext<DeleteCommandRequest>> {
    private val idValidator = ValidatorStringNonEmpty(message = "ID is empty", field = "id")

    override fun validate(ctx: RequestContext<DeleteCommandRequest>): ValidationResult {
        return ValidationResult(
            errors = listOf(
                *idValidator.validate(ctx.request.requestModelId.id).errors.toTypedArray(),
            )
        )
    }
}
