package com.deskshare.cor.worker

import com.deskshare.cor.CorExecInterface
import com.deskshare.cor.CorWorkerInterface

abstract class CorChainAbstract<T>(
    override val title: String,
    override val description: String,
    val workers: List<CorExecInterface<T>>,
    val blockSupports: T.() -> Boolean = { false },
    val blockOnError: T.(e: Throwable) -> Unit = { e: Throwable -> throw e }
) : CorWorkerInterface<T> {
    override suspend fun supports(ctx: T) = blockSupports(ctx)
    override suspend fun onError(ctx: T, e: Throwable) = blockOnError(ctx, e)
    abstract override suspend fun handle(ctx: T)
}
