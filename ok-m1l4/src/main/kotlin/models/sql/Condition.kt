package models.sql

abstract class Condition {
    fun and(initializer: Condition.() -> Unit) {
        addCondition(And().apply(initializer))
    }

    fun or(initializer: Condition.() -> Unit) {
        addCondition(Or().apply(initializer))
    }

    infix fun String.eq(value: Any?) {
        addCondition(Eq(this, value))
    }

    infix fun String.nonEq(value: Any?) {
        addCondition(NotEq(this, value))
    }

    protected abstract fun addCondition(condition: Condition)
}
