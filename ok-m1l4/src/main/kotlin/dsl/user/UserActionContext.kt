package dsl.user

import models.user.Action

@UserDsl
class UserActionContext {
    private val _actions: MutableSet<Action> = mutableSetOf()

    val actions: Set<Action>
        get() = _actions.toSet()

    fun add(action: Action) = _actions.add(action)

    fun add(action: String) = add(Action.valueOf(action))

    operator fun Action.unaryPlus() = add(this)

    operator fun String.unaryPlus() = add(this)
}
