package com.deskshare.cor.worker

import com.deskshare.cor.CorWorkerInterface

class CorWorker<T>(
    override val title: String,
    override val description: String,
    val blockSupports: T.() -> Boolean = { false },
    val blockHandle: T.() -> Unit = {},
    val blockOnError: T.(e: Throwable) -> Unit = { e: Throwable -> throw e },
    val blockLogger: T.(msg: String) -> Unit
) : CorWorkerInterface<T> {
    override suspend fun supports(ctx: T): Boolean = blockSupports(ctx)
    override suspend fun handle(ctx: T) = blockHandle(ctx)
    override suspend fun onError(ctx: T, e: Throwable) = blockOnError(ctx, e)
    override suspend fun log(ctx: T, msg: String) = blockLogger(ctx, msg)
}
