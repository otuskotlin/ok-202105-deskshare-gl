package com.deskshare.logics.worker

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.RequestContextStatus
import com.deskshare.common.context.RequestInterface
import com.deskshare.cor.dsl.CorExecDslInterface
import com.deskshare.cor.dsl.chain
import com.deskshare.cor.dsl.worker

class FinishWorker<T : RequestInterface> :
    CorExecDslInterface<RequestContext<T>> by chain({
        worker {
            title = "Chain finished Ok"
            supports { status in setOf(RequestContextStatus.Running, RequestContextStatus.Finishing) }
            handle {
                status = RequestContextStatus.Success
            }
        }

        worker {
            title = "Chain finished not Ok"
            supports { !isSuccess() }
            handle {
                status = RequestContextStatus.Error
            }
        }
    })
