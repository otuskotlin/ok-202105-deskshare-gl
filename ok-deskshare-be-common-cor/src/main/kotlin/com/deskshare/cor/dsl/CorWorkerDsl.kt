package com.deskshare.cor.dsl

import com.deskshare.cor.CorExecInterface
import com.deskshare.cor.worker.CorWorker

fun <T> worker(function: CorWorkerDsl<T>.() -> Unit) = CorWorkerDsl<T>().apply(function)

@CorDslMarker
data class CorWorkerConfigurationDsl<T>(var logging: Boolean = true)

@CorDslMarker
fun <T> CorChainDslInterface<T>.configuration(block: CorWorkerConfigurationDsl<T>.() -> Unit) {
    addConfig(CorWorkerConfigurationDsl<T>().apply(block))
}

@CorDslMarker
fun <T> CorWorkerDslInterface<T>.configuration(block: CorWorkerConfigurationDsl<T>.() -> Unit) {
    addConfig(CorWorkerConfigurationDsl<T>().apply(block))
}

@CorDslMarker
fun <T> CorChainDslInterface<T>.worker(block: CorWorkerDsl<T>.() -> Unit) {
    add(CorWorkerDsl<T>().apply(block))
}

@CorDslMarker
class CorWorkerDsl<T>(
    override var title: String = "",
    override var description: String = "",
    private var blockSupports: T.() -> Boolean = { false },
    private var blockHandle: T.() -> Unit = {},
    private var blockOnError: T.(e: Throwable) -> Unit = { e: Throwable -> throw e },
    private var blockLogger: T.(msg: String) -> Unit = {},
    private var config: CorWorkerConfigurationDsl<T> = CorWorkerConfigurationDsl()
) : CorWorkerDslInterface<T> {
    override fun addConfig(config: CorWorkerConfigurationDsl<T>) {
        this.config = config
    }

    override fun build(): CorExecInterface<T> = CorWorker<T>(
        title = title,
        description = description,
        blockSupports = blockSupports,
        blockHandle = blockHandle,
        blockOnError = blockOnError,
        blockLogger = if (config.logging) {blockLogger} else {{}}
    )

    override fun logger(block: T.(msg: String) -> Unit) {
        blockLogger = block
    }

    override fun handle(block: T.() -> Unit) {
        blockHandle = block
    }

    override fun supports(block: T.() -> Boolean) {
        blockSupports = block
    }

    override fun onError(block: T.(e: Throwable) -> Unit) {
        blockOnError = block
    }
}
