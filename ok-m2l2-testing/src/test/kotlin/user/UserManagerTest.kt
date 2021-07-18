package user

import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import kotlin.test.assertEquals

class UserManagerTest {
    @Test
    fun `user provider test`() {
        val mockUserProvider = mock<UserProvider> {
            on { getOne("test") } doReturn UserTestBuilder.validUser(username = "test")
        }

        val userManager = UserManager(mockUserProvider)
        val user = userManager.getByUsername("test")
        assertEquals("test", user.username)
    }
}
