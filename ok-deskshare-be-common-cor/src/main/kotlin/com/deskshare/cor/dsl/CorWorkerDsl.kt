package com.deskshare.cor.dsl

import com.deskshare.cor.CorExecInterface
import com.deskshare.cor.worker.CorWorker

@CorDslMarker
data class CorWorkerConfigurationDsl<T>(
    var logger: T.(msg: String) -> Unit = { msg: String -> },
    var logPropagation: Boolean = true
) {
    fun logger(block: T.(msg: String) -> Unit) {
        logger = block
    }
}

@CorDslMarker
fun <T> CorChainDslInterface<T>.configuration(block: CorWorkerConfigurationDsl<T>.() -> Unit) {
    addConfig(CorWorkerConfigurationDsl<T>().apply(block))
}

@CorDslMarker
fun <T> CorChainDslInterface<T>.worker(block: CorWorkerDsl<T>.() -> Unit) {
    val worker = CorWorkerDsl<T>().apply(block)
    worker.logger(getConfig().logger)
    add(worker)
}

@CorDslMarker
class CorWorkerDsl<T>(
    override var title: String = "",
    override var description: String = "",
    private var blockSupports: T.() -> Boolean = { false },
    private var blockHandle: T.() -> Unit = {},
    private var blockOnError: T.(e: Throwable) -> Unit = { e: Throwable -> throw e },
    private var blockLogger: T.(msg: String) -> Unit = { msg: String -> }
) : CorWorkerDslInterface<T> {
    override fun build(): CorExecInterface<T> = CorWorker<T>(
        title = title,
        description = description,
        blockSupports = blockSupports,
        blockHandle = blockHandle,
        blockOnError = blockOnError,
        blockLogger = blockLogger
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
