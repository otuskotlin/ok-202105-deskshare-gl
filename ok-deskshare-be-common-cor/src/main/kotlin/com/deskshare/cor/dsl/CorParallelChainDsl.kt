package com.deskshare.cor.dsl

import com.deskshare.cor.CorExecInterface
import com.deskshare.cor.worker.CorChainParallel

@CorDslMarker
fun <T> CorChainDslInterface<T>.parallel(block: CorParallelChainDsl<T>.() -> Unit) {
    add(CorParallelChainDsl<T>().apply(block))
}

@CorDslMarker
class CorParallelChainDsl<T>(
    override var title: String = "",
    override var description: String = "",
    private val workers: MutableList<CorExecDslInterface<T>> = mutableListOf(),
    private var blockSupports: T.() -> Boolean = { true },
    private var blockOnError: T.(e: Throwable) -> Unit = { e: Throwable -> throw e },
    private var blockLogger: T.(msg: String) -> Unit = { },
    private var config: CorWorkerConfigurationDsl<T> = CorWorkerConfigurationDsl()
) : CorChainDslInterface<T> {
    override fun addConfig(config: CorWorkerConfigurationDsl<T>) {
        this.config = config
    }

    override fun logger(block: T.(msg: String) -> Unit) {
        blockLogger = block
    }

    override fun add(worker: CorExecDslInterface<T>) {
        worker.logger(blockLogger)
        workers.add(worker)
    }

    override fun supports(block: T.() -> Boolean) {
        blockSupports = block
    }

    override fun onError(block: T.(e: Throwable) -> Unit) {
        blockOnError = block
    }

    override fun build(): CorExecInterface<T> = CorChainParallel<T>(
        title = title,
        description = description,
        workers = workers.map { it.build() },
        blockSupports = blockSupports,
        blockOnError = blockOnError,
        blockLogger = if (config.logging) {blockLogger} else {{}}
    )
}
