package user

class UserManager(private val provider: UserProvider) {
    fun register(
        username: String,
        password: String,
        role: UserRole = UserRole.READ
    ): Unit = provider.saveUser(User(username, password, role))

    fun getByUsername(username: String): User = provider.getOne(username)
    fun getAll(): Collection<User> = provider.getAll()
}
