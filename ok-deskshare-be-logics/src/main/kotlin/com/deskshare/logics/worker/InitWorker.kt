package com.deskshare.logics.worker

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.RequestContextStatus
import com.deskshare.common.context.RequestInterface
import com.deskshare.cor.dsl.CorExecDslInterface
import com.deskshare.cor.dsl.worker

class InitWorker<T : RequestInterface> :
    CorExecDslInterface<RequestContext<T>> by worker({
        title = "Init chain worker"
        description = "At start of a chain status mus be defined as running"
        supports {
            status == RequestContextStatus.None
        }
        handle {
            status = RequestContextStatus.Running
        }
    })
