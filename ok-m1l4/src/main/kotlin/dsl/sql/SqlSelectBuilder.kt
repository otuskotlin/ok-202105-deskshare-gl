package dsl.sql

import models.sql.*

@DslMarker
annotation class SqlDsl

@SqlDsl
class SqlSelectBuilder {
    private var select = Select.DEFAULT;
    private var from = From.DEFAULT
    private var condition: Condition? = null

    fun select(vararg fields: String) {
        select = Select(fields.toSet())
    }

    fun from(table: String) {
        from = From(table)
    }

    fun where(block: Condition.() -> Unit) {
        condition = And().apply(block)
    }

    fun build(): String {
        val statement = Statement(
            select = select,
            from = from,
            condition = condition,
        )
        if (!statement.isValid()) {
            throw Exception()
        }
        return statement.toString()
    }
}

@SqlDsl
fun query(block: SqlSelectBuilder.() -> Unit) = SqlSelectBuilder().apply(block)
