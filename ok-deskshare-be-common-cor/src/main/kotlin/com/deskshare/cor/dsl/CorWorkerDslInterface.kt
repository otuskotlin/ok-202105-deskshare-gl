package com.deskshare.cor.dsl

interface CorWorkerDslInterface<T> : CorExecDslInterface<T> {
    fun handle(function: T.() -> Unit)
    fun supports(block: T.() -> Boolean)
    fun onError(block: T.(e: Throwable) -> Unit)
}
