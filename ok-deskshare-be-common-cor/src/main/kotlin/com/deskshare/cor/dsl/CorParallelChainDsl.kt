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
    private var config: CorWorkerConfigurationDsl<T> = CorWorkerConfigurationDsl<T>()
): CorChainDslInterface<T> {

    override fun getConfig(): CorWorkerConfigurationDsl<T> = config

    override fun addConfig(config: CorWorkerConfigurationDsl<T>) {
        this.config = config
    }

    override fun add(worker: CorExecDslInterface<T>) {
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
        blockLogger = config.logger
    )
}
