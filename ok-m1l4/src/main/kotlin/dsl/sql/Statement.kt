package dsl.sql

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

    fun render(): String {
        val parts = arrayListOf(
            "select ${select.render()}",
            "from ${from.redner()}",
        )
        if (condition != null) {
            parts.add("where ${condition.render()}")
        }
        return parts.joinToString(" ");
    }
}
