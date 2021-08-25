package com.deskshare.service

import com.deskshare.common.context.CommandRequestContext
import com.deskshare.common.context.command.CreateCommand
import com.deskshare.common.context.command.DeleteCommand
import com.deskshare.common.context.command.UpdateCommand
import com.deskshare.common.models.ReservationModel

interface ReservationCommandServiceInterface {
    suspend fun <T : CreateCommand> create(ctx: CommandRequestContext<T>): ReservationModel
    suspend fun <T : UpdateCommand> update(ctx: CommandRequestContext<T>): ReservationModel
    suspend fun <T : DeleteCommand> delete(ctx: CommandRequestContext<T>): ReservationModel
}
