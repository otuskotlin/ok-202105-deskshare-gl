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
                log(ctx, "start $title")
                handle(ctx)
                log(ctx, "end $title")
            } catch (e: Throwable) {
                log(ctx, "error at $title")
                onError(ctx, e)
            }
        }
    }
}
