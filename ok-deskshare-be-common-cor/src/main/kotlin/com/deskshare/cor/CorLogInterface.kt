package com.deskshare.cor

interface CorLogInterface<T> {
    suspend fun log(ctx: T, msg: String)
}
