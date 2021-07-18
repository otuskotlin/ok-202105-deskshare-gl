package user

class UserTestBuilder {

    companion object Factory {
        const val defaultName = "name"
        const val defaultPassword = "123456"
        val defaultRole = UserRole.WRITE

        fun userAdmin(
            username: String = defaultName,
            password: String = defaultPassword
        ): User {
            return userWithRole(username = username, password = password, role = UserRole.ADMIN)
        }

        fun userWrite(
            username: String = defaultName,
            password: String = defaultPassword
        ): User {
            return userWithRole(username = username, password = password, role = UserRole.WRITE)
        }

        fun userRead(
            username: String = defaultName,
            password: String = defaultPassword
        ): User {
            return userWithRole(username = username, password = password, role = UserRole.READ)
        }

        fun userWithRole(
            role: UserRole,
            username: String = defaultName,
            password: String = defaultPassword
        ): User {
            return validUser(username, password, role)
        }

        fun validUser(
            username: String = defaultName,
            password: String = defaultPassword,
            role: UserRole = defaultRole
        ): User {
            return User(username, password, role)
        }

        fun invalidUser(): User {
            val user = User(defaultName, defaultPassword, defaultRole)
            user.copy(username = "", password = "")
            return user
        }
    }
}
