package user

interface UserProvider {
    fun saveUser(user: User): Unit
    fun getOne(username:  String): User
    fun getAll(): Collection<User>
}
