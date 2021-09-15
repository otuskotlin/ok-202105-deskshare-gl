package com.deskshare.logics.validation

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.command.CreateCommandRequest
import com.deskshare.validation.ValidationResult
import com.deskshare.validation.ValidatorInterface
import com.deskshare.validation.validator.ValidatorStringNonEmpty

class CreateReservationValidator: ValidatorInterface<RequestContext<CreateCommandRequest>> {
    private val userValidator = ValidatorStringNonEmpty(message = "User ID is empty", field = "userId")
    private val workspaceValidator = ValidatorStringNonEmpty(message = "Workspace ID is empty", field = "workspace")
    private val descriptionValidator = ValidatorStringNonEmpty(message = "Description is empty", field = "description")
    private val dateValidator = ReservationDatePeriodValidator()

    override fun validate(ctx: RequestContext<CreateCommandRequest>): ValidationResult {
        return ValidationResult(
            errors = listOf(
                *userValidator.validate(ctx.request.requestModel.userId.id).errors.toTypedArray(),
                *workspaceValidator.validate(ctx.request.requestModel.workspaceId.id).errors.toTypedArray(),
                *descriptionValidator.validate(ctx.request.requestModel.description).errors.toTypedArray(),
                *dateValidator.validate(ctx.request.requestModel).errors.toTypedArray(),
            )
        )
    }
}
