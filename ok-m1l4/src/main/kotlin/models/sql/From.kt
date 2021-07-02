package models.sql

class From(private val table: String) {
    companion object {
        val DEFAULT = From("")
    }

    fun isValid(): Boolean {
        return table.isNotEmpty()
    }

    override fun toString(): String {
        return "from $table"
    }
}
