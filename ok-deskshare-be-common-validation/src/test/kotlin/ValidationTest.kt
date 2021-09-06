import com.deskshare.validation.validator.ValidatorFutureDate
import com.deskshare.validation.validator.ValidatorStringNonEmpty
import java.time.LocalDateTime
import kotlin.test.*

class ValidationTest {
    @Test
    fun `string not empty validation Ok`() {
        val validator = ValidatorStringNonEmpty(field = "", message = "")

        assertTrue(validator.validate("123").isSuccess)
    }

    @Test
    fun `string not empty validation Not Ok`() {
        val validator = ValidatorStringNonEmpty()
        val result = validator.validate("")
        assertFalse(result.isSuccess)
        assertEquals(1, result.errors.size)
    }

    @Test
    fun `test date in the future OK`() {
        val validator = ValidatorFutureDate()
        val result = validator.validate(value = LocalDateTime.MAX)

        assertTrue(result.isSuccess)
    }

    @Test
    fun `test date in the future Not OK`() {
        val validator = ValidatorFutureDate()
        val result = validator.validate(value = LocalDateTime.MIN)

        assertFalse(result.isSuccess)
        assertEquals(1, result.errors.size)
    }
}
