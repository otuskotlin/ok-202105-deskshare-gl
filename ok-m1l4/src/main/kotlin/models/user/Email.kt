package models.user

@JvmInline
value class Email(val id: String) {
    companion object {
        val NONE = Email("")
    }
}
