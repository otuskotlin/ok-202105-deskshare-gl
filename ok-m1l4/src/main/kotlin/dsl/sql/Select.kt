package dsl.sql

class Select(private val fields: Set<String>) {
    companion object {
        val DEFAULT = Select(setOf("*"))
    }

    fun isValid(): Boolean {
        return fields.isNotEmpty()
    }

    fun render(): String {
        return fields.joinToString(", ")
    }
}
