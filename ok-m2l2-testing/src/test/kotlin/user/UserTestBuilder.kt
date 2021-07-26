package user

class UserTestBuilder {
    companion object {
        const val defaultName = "name"
        const val defaultPassword = "123456"
        val defaultRole = UserRole.WRITE

        fun userAdmin(
            username: String = defaultName,
            password: String = defaultPassword
        ) = User.admin(username, password)

        fun userWrite(
            username: String = defaultName,
            password: String = defaultPassword
        ) = User.write(username, password)

        fun userRead(
            username: String = defaultName,
            password: String = defaultPassword
        ) = User.read(username, password)

        fun validUser(
            username: String = defaultName,
            password: String = defaultPassword,
            role: UserRole = defaultRole
        ) = User(username, password, role)

        fun invalidUser(): User {
            val user = User(defaultName, defaultPassword, defaultRole)
            user.copy(username = "", password = "")
            return user
        }

        fun list(count: Int = 10) = arrayListOf<User>()
            .apply { repeat(count) { add(UserTestBuilder.userAdmin()) } }

    }
}
