package user

data class User(val username: String, val password: String, val role: UserRole) {
    fun canEdit(): Boolean = when(role) {
        UserRole.ADMIN, UserRole.WRITE -> true
        UserRole.READ -> false
    }

    fun isValid(): Boolean = username.isNotEmpty() && password.isNotEmpty()
}
