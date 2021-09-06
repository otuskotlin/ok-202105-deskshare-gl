package com.deskshare.validation

class ValidationResult(val errors: List<ValidationErrorInterface>) {
    val isSuccess: Boolean
        get() = errors.isEmpty()

    companion object {
        val SUCCESS = ValidationResult(emptyList())
        fun fromError(e: ValidationErrorInterface) = ValidationResult(listOf(e))
    }
}
