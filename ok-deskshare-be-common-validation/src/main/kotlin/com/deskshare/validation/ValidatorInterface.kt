package com.deskshare.validation

interface ValidatorInterface<T> {
    fun validate(value: T): ValidationResult
}
