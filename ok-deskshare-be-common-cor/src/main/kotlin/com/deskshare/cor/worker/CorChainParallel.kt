package com.deskshare.cor.worker

import com.deskshare.cor.CorExecInterface
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class CorChainParallel<T>(
    title: String,
    description: String,
    workers: List<CorExecInterface<T>>,
    blockSupports: T.() -> Boolean = { false },
    blockOnError: T.(e: Throwable) -> Unit = { e: Throwable -> throw e }
) : CorChainAbstract<T>(title, description, workers, blockSupports, blockOnError) {
    override suspend fun handle(ctx: T) = coroutineScope {
        workers
            .map { launch { it.exec(ctx) } }
            .toList()
            .forEach { it.join() }

    }
}
