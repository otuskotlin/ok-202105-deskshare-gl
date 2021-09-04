package com.deskshare.cor.dsl

interface CorChainDslInterface<T>: CorExecDslInterface<T> {
    fun supports(block: T.() -> Boolean)
    fun onError(block: T.(e: Throwable) -> Unit)
    fun add(worker: CorExecDslInterface<T>)
}
