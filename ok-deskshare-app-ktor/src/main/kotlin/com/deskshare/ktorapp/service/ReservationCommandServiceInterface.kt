package com.deskshare.ktorapp.service

import com.deskshare.common.context.CommandContext
import com.deskshare.common.context.command.CreateCommand
import com.deskshare.common.context.command.DeleteCommand
import com.deskshare.common.context.command.UpdateCommand

interface ReservationCommandServiceInterface {
    fun <T : CreateCommand> create(ctx: CommandContext<T>): CommandContext<T>
    fun <T : UpdateCommand> update(ctx: CommandContext<T>): CommandContext<T>
    fun <T : DeleteCommand> delete(ctx: CommandContext<T>): CommandContext<T>
}
