package com.deskshare.cor

interface CorExecInterface<T> {
    suspend fun exec(ctx: T)
}
