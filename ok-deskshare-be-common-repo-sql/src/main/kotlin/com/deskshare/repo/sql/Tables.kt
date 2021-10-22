package com.deskshare.repo.sql

import com.deskshare.common.models.*
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.statements.InsertStatement
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object ReservationsTable: Table(name = "Reservations") {
    val id = uuid("id").autoGenerate().uniqueIndex()
    val description = varchar("description", 512)
    val userId = reference("user_id", UsersTable.id).index()
    val workspaceId = reference("workspace_id", WorkspacesTable.id).index()
    val status = enumeration("status", ReservationStatus::class)
    val from = varchar("from_datetime", 50)
    val until = varchar("until_datetime", 50)
    val createdAt = varchar("created_datetime", 50)

    override val primaryKey = PrimaryKey(id)

    fun from(res: InsertStatement<Number>) = ReservationModel(
        id = ReservationIdModel(res[id]),
        description = res[description],
        userId = UserIdModel(res[userId]),
        workspaceId = WorkspaceIdModel(res[workspaceId]),
        status = res[status],
        from = LocalDateTime.parse(res[from], DateTimeFormatter.ISO_DATE_TIME),
        until = LocalDateTime.parse(res[until], DateTimeFormatter.ISO_DATE_TIME),
        createdAt = LocalDateTime.parse(res[createdAt], DateTimeFormatter.ISO_DATE_TIME)
    )

    fun from(res: ResultRow) = ReservationModel(
        id = ReservationIdModel(res[id]),
        description = res[description],
        userId = UserIdModel(res[userId]),
        workspaceId = WorkspaceIdModel(res[workspaceId]),
        status = res[status],
        from = LocalDateTime.parse(res[from], DateTimeFormatter.ISO_DATE_TIME),
        until = LocalDateTime.parse(res[until], DateTimeFormatter.ISO_DATE_TIME),
        createdAt = LocalDateTime.parse(res[createdAt], DateTimeFormatter.ISO_DATE_TIME)
    )
}

object UsersTable : Table("Users") {
    val id = uuid("id").autoGenerate().uniqueIndex()
    val name = varchar("name", 128)

    override val primaryKey = PrimaryKey(id)
}

object WorkspacesTable : Table("Workspaces<") {
    val id = uuid("id").autoGenerate().uniqueIndex()
    val name = varchar("name", 128)

    override val primaryKey = PrimaryKey(id)
}
