package com.deskshare.logics.worker

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.RequestContextStatus
import com.deskshare.common.context.RequestInterface
import com.deskshare.cor.dsl.CorChainDslInterface
import com.deskshare.cor.dsl.worker

internal fun <T : RequestInterface> CorChainDslInterface<RequestContext<T>>.initChainWorker(
    title: String = "Init chain worker"
) = worker {
    this.title = title
    description = "At start of a chain status mus be defined as running"
    supports {
        status == RequestContextStatus.None
    }
    handle {
        status = RequestContextStatus.Running
    }
}
