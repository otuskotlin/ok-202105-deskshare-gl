package com.deskshare.cor

interface CorWorkerInterface<T> : CorExecInterface<T>, CorLogInterface<T> {
    val title: String
    val description: String
    suspend fun supports(ctx: T): Boolean
    suspend fun handle(ctx: T)
    suspend fun onError(ctx: T, e: Throwable)
    override suspend fun exec(ctx: T) {
        if (supports(ctx)) {
            try {
                log(ctx, "Start: $title")
                handle(ctx)
                log(ctx, "Stop Ok: $title")
            } catch (e: Throwable) {
                log(ctx, "Stop !Ok: $title")
                onError(ctx, e)
            }
        }
    }
}
