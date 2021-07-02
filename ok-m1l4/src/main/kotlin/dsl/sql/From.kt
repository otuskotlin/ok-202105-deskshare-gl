package dsl.sql

class From(private val table: String) {
    companion object {
        val DEFAULT = From("")
    }

    fun isValid(): Boolean {
        return table.isNotEmpty()
    }

    fun redner(): String {
        return table
    }
}
