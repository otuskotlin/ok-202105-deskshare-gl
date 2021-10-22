package com.deskshare.common.repo.response

import com.deskshare.common.models.error.CommonError

interface DbResponseInterface<T> {
    val isSuccess: Boolean
    val errors: List<CommonError>
    val result: T
}
