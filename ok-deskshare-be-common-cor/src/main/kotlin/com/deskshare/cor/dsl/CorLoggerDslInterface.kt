package com.deskshare.cor.dsl

interface CorLoggerDslInterface<T> {
    fun logger(block: T.(msg: String) -> Unit)
}
