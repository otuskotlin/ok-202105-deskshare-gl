package models.user

import java.time.LocalDateTime

data class User(
    val id: UserId,
    val firstName: String,
    val lastName: String,
    val available: List<LocalDateTime>,
    val email: Email,
    val phone: Phone,
    val action:  Set<Action>,
)
