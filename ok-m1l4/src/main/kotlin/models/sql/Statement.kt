package models.sql

data class Statement(
    val select: Select,
    val from: From,
    val condition: Condition?

) {
    fun isValid(): Boolean {
        if (!select.isValid()) {
            return false
        }
        if (!from.isValid()) {
            return false
        }
        return true
    }

    override fun toString(): String {
        val parts = arrayListOf(
            select.toString(),
            from.toString(),
        )
        if (condition != null) {
            parts.add("where ${condition.toString()}")
        }
        return parts.joinToString(" ");
    }
}
