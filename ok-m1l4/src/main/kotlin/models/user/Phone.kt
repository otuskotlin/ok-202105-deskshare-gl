package models.user

@JvmInline
value class Phone(val id: String) {
    companion object {
        val NONE = Phone("")
    }
}
