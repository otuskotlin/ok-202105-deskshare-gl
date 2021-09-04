package com.deskshare.cor

interface CorWorkerInterface<T> : CorExecInterface<T> {
    val title: String
    val description: String
    suspend fun supports(ctx: T): Boolean
    suspend fun handle(ctx: T)
    suspend fun onError(ctx: T, e: Throwable)
    override suspend fun exec(ctx: T) {
        if (supports(ctx)) {
            try {
                handle(ctx)
            } catch (e: Throwable) {
                onError(ctx, e)
            }
        }
    }
}
