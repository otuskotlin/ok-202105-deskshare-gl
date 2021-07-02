package dsl.sql

class NotEq(private val column: String, private val value: Any?) : Condition() {

    init {
        if (value != null && value !is Number && value !is String) {
            throw IllegalArgumentException("Only <null>, numbers and strings values can be used in the 'where' clause")
        }
    }

    override fun addCondition(condition: Condition) {
        throw IllegalStateException("Can't add a nested condition to the sql 'noteq'")
    }

    override fun render(): String {
        return when (value) {
            null -> "$column !is null"
            is String -> "$column != '$value'"
            else -> "$column != $value"
        }
    }
}
