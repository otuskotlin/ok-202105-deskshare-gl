import org.junit.jupiter.api.BeforeAll
import kotlin.test.Test
import kotlin.test.assertEquals

class JUnitTest {
    @Test
    fun junit5Test() {
        assertEquals("1", 1.toString())
    }

    companion object {
        @BeforeAll
        @JvmStatic
        fun tearUp() {
            println("Before all")
        }
    }
}
