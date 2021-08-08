package com.deskshare.common.context

sealed interface IContextStatus
object ContextStatusSuccess : IContextStatus
object ContextStatusFailure: IContextStatus
object ContextStatusPending: IContextStatus
