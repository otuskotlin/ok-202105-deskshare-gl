package com.deskshare.logics.config

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.RequestInterface
import com.deskshare.cor.dsl.CorChainDslInterface
import com.deskshare.cor.dsl.configuration

internal fun <T : RequestInterface> CorChainDslInterface<RequestContext<T>>.chainConfiguration() = configuration {
    logging = true
}
