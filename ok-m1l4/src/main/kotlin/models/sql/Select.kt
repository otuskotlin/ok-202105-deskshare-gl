package models.sql

class Select(private val fields: Set<String>) {
    companion object {
        val DEFAULT = Select(setOf("*"))
    }

    fun isValid(): Boolean {
        return fields.isNotEmpty()
    }

    override fun toString(): String {
        return "select ${fields.joinToString(", ")}"
    }
}
