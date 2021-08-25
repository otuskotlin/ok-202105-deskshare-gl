package com.deskshare.common.context

import com.deskshare.common.context.command.CommandInterface
import com.deskshare.common.context.command.CreateCommand
import com.deskshare.common.context.command.DeleteCommand
import com.deskshare.common.context.command.UpdateCommand
import com.deskshare.common.models.ReservationIdModel
import com.deskshare.common.models.ReservationModel

class CommandRequestContext<T : CommandInterface>(val command: T, requestId: String = "") : RequestContext(requestId) {
    companion object {
        fun forCreateReservation(newModel: ReservationModel, requestId: String = "") =
            CommandRequestContext(CreateCommand(newModel), requestId)

        fun forUpdateReservation(oldModel: ReservationModel, requestId: String = "") =
            CommandRequestContext(UpdateCommand(oldModel), requestId)

        fun forDeleteReservation(id: String, requestId: String = "") =
            CommandRequestContext(DeleteCommand(ReservationIdModel(id)), requestId)
    }
}
