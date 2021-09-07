package com.deskshare.cor.dsl

interface CorWorkerDslInterface<T> : CorExecDslInterface<T>, CorConfigurableDslInterface<T> {
    fun handle(function: T.() -> Unit)
    fun supports(block: T.() -> Boolean)
    fun onError(block: T.(e: Throwable) -> Unit)
}
