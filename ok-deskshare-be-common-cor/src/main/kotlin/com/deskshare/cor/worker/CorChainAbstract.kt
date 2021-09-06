package com.deskshare.cor.worker

import com.deskshare.cor.CorExecInterface
import com.deskshare.cor.CorWorkerInterface

abstract class CorChainAbstract<T>(
    override val title: String,
    override val description: String,
    val workers: List<CorExecInterface<T>>,
    val blockSupports: T.() -> Boolean = { false },
    val blockOnError: T.(e: Throwable) -> Unit = { e: Throwable -> throw e },
    val blockLogger: T.(msg: String) -> Unit
) : CorWorkerInterface<T> {
    override suspend fun supports(ctx: T) = blockSupports(ctx)
    override suspend fun onError(ctx: T, e: Throwable) = blockOnError(ctx, e)
    override suspend fun log(ctx: T, msg: String) = blockLogger(ctx, msg)
    abstract override suspend fun handle(ctx: T)
}
