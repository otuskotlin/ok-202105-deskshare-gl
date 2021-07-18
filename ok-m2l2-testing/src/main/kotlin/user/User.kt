package user

data class User(val username: String, val password: String, val role: UserRole) {
    companion object {
        fun admin(username: String, password: String) = User(username, password, UserRole.ADMIN)
        fun read(username: String, password: String) = User(username, password, UserRole.READ)
        fun write(username: String, password: String) = User(username, password, UserRole.WRITE)
    }

    fun canEdit(): Boolean = when (role) {
        UserRole.ADMIN, UserRole.WRITE -> true
        UserRole.READ -> false
    }

    fun isValid(): Boolean = username.isNotEmpty() && password.isNotEmpty()
}
