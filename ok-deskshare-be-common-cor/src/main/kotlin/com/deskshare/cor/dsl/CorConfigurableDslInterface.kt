package com.deskshare.cor.dsl

interface CorConfigurableDslInterface<T> {
    fun addConfig(config: CorWorkerConfigurationDsl<T>)
}
