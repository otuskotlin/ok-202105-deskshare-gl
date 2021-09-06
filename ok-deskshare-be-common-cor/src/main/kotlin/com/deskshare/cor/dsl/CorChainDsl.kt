package com.deskshare.cor.dsl

import com.deskshare.cor.CorExecInterface
import com.deskshare.cor.worker.CorChain

fun <T> chain(block: CorChainDsl<T>.() -> Unit): CorChainDsl<T> = CorChainDsl<T>().apply(block)

@CorDslMarker
fun <T> CorChainDslInterface<T>.chain(block: CorChainDslInterface<T>.() -> Unit) {
    add(CorChainDsl<T>().apply(block))
    addConfig(getConfig())
}

@CorDslMarker
class CorChainDsl<T>(
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

    override fun build(): CorExecInterface<T> = CorChain<T>(
        title = title,
        description = description,
        workers = workers.map { it.build() },
        blockSupports = blockSupports,
        blockOnError = blockOnError,
        blockLogger = config.logger
    )
}
