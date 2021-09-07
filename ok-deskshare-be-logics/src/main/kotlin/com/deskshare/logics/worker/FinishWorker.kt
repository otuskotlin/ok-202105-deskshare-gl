package com.deskshare.logics.worker

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.RequestContextStatus
import com.deskshare.common.context.RequestInterface
import com.deskshare.cor.dsl.CorChainDslInterface
import com.deskshare.cor.dsl.chain
import com.deskshare.cor.dsl.worker

internal fun <T : RequestInterface> CorChainDslInterface<RequestContext<T>>.finishChainWorker(
    title: String = "Finish chain worker"
) = chain {
    this.title  = title
    worker {
        this.title = "Chain finished Ok"
        supports { status in setOf(RequestContextStatus.Running, RequestContextStatus.Finishing) }
        handle {
            status = RequestContextStatus.Success
        }
    }
    worker {
        this.title = "Chain finished not Ok"
        supports { !isSuccess() }
        handle {
            status = RequestContextStatus.Error
        }
    }
}
