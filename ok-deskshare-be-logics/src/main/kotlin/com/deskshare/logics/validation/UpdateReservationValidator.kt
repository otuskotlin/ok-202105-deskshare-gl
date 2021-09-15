package com.deskshare.logics.validation

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.command.UpdateCommandRequest
import com.deskshare.validation.ValidationResult
import com.deskshare.validation.ValidatorInterface
import com.deskshare.validation.validator.ValidatorStringNonEmpty

class UpdateReservationValidator: ValidatorInterface<RequestContext<UpdateCommandRequest>> {
    private val idValidator = ValidatorStringNonEmpty(message = "ID is empty", field = "id")
    private val userValidator = ValidatorStringNonEmpty(message = "User ID is empty", field = "userId")
    private val workspaceValidator = ValidatorStringNonEmpty(message = "Workspace ID is empty", field = "workspace")
    private val descriptionValidator = ValidatorStringNonEmpty(message = "Description is empty", field = "description")
    private val dateValidator = ReservationDatePeriodValidator()

    override fun validate(ctx: RequestContext<UpdateCommandRequest>): ValidationResult {
        return ValidationResult(
            errors = listOf(
                *idValidator.validate(ctx.request.requestModel.id.id).errors.toTypedArray(),
                *userValidator.validate(ctx.request.requestModel.userId.id).errors.toTypedArray(),
                *workspaceValidator.validate(ctx.request.requestModel.workspaceId.id).errors.toTypedArray(),
                *descriptionValidator.validate(ctx.request.requestModel.description).errors.toTypedArray(),
                *dateValidator.validate(ctx.request.requestModel).errors.toTypedArray(),
            )
        )
    }
}
