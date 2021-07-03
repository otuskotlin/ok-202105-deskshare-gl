import dsl.user.user
import models.user.Action
import org.junit.Test
import kotlin.test.assertEquals

class UserDslTestCase {
    @Test
    fun `user builder`() {
        val user = user {
            name {
                first = "Grischa"
                last = "Liberchuk"
            }
            contacts {
                email = "mail@mail.de"
                phone = "123235"
            }
            actions {
                add(Action.DELETE)
                add("UPDATE")

                +Action.CREATE
                +"READ"
            }
            available {
                tuesday("12:00")
                friday("23:00")
            }
        }

        assertEquals(user.firstName, "Grischa")
    }
}
