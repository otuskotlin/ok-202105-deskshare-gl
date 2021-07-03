package dsl.sql

open class CompositeCondition(private val sqlOperator: String) : Condition() {
    private val conditions = mutableListOf<Condition>()

    override fun addCondition(condition: Condition) {
        conditions += condition
    }

    override fun render(): String {
        return if (conditions.size == 1) {
            conditions.first().render()
        } else {
            conditions.joinToString(prefix = "(", postfix = ")", separator = " $sqlOperator ") {
                it.render()
            }
        }
    }
}

class And : CompositeCondition("and")

class Or : CompositeCondition("or")
