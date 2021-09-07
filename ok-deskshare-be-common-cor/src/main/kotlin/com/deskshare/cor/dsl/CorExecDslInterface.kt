package com.deskshare.cor.dsl

import com.deskshare.cor.CorExecInterface

interface CorExecDslInterface<T>: CorLoggerDslInterface<T> {
    var title: String
    var description: String
    fun build(): CorExecInterface<T>
}
