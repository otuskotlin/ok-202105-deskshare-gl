package dsl.sql

data class Statement(
    private val select: Select,
    private val from: From,
    private val condition: Condition?
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
