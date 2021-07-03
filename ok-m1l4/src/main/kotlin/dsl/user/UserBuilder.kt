package dsl.user

import models.user.*
import java.time.LocalDateTime

@DslMarker
annotation class UserDsl

@UserDsl
class UserBuilder {
    var id = UserId.random()
    var firstName = ""
    var lastName = ""
    val available = mutableListOf<LocalDateTime>()
    var email = Email.NONE
    var phone = Phone.NONE
    val action = mutableSetOf<Action>()

    @UserDsl
    fun name(block: UserNameContext.() -> Unit) {
        val context = UserNameContext().apply(block);
        firstName = context.first
        lastName = context.last
    }

    @UserDsl
    fun contacts(block: UserContactContext.() -> Unit) {
        val context = UserContactContext().apply(block);
        email = Email(context.email)
        phone = Phone(context.phone)
    }

    @UserDsl
    fun actions(block: UserActionContext.() -> Unit) {
        val context = UserActionContext().apply(block);
        action.addAll(context.actions)
    }

    @UserDsl
    fun available(block: UserAvailableContext.() -> Unit) {
        val context = UserAvailableContext().apply(block)
        available.addAll(context.availableList)
    }

    fun build() = User(
        id = id ,
        firstName =  firstName,
        lastName =  lastName,
        available = available,
        email = email,
        phone = phone,
        action = action,
    )
}

@UserDsl
fun user(block: UserBuilder.() -> Unit) = UserBuilder().apply(block).build()
