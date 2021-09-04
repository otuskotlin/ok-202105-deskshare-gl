package com.deskshare.cor.dsl

import com.deskshare.cor.CorExecInterface
import com.deskshare.cor.worker.CorWorker

@CorDslMarker
fun <T> CorChainDslInterface<T>.worker(block: CorWorkerDsl<T>.() -> Unit) {
    add(CorWorkerDsl<T>().apply(block))
}

@CorDslMarker
fun <T> CorChainDslInterface<T>.worker(title: String, description: String, block: T.() -> Unit) {
    add(
        CorWorkerDsl<T>(
            title = title,
            description = description,
            blockHandle = block
        )
    )
}

@CorDslMarker
class CorWorkerDsl<T>(
    override var title: String = "",
    override var description: String = "",
    private var blockSupports: T.() -> Boolean = { false },
    private var blockHandle: T.() -> Unit = {},
    private var blockOnError: T.(e: Throwable) -> Unit = { e: Throwable -> throw e }
) : CorWorkerDslInterface<T> {
    override fun build(): CorExecInterface<T> = CorWorker<T>(
        title = title,
        description = description,
        blockSupports = blockSupports,
        blockHandle = blockHandle,
        blockOnError = blockOnError
    )

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
