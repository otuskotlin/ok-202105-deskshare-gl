import com.deskshare.common.context.Context
import com.deskshare.common.context.IContextStatus
import com.deskshare.common.context.LocaleModel
import com.deskshare.common.models.error.CommonError
import kotlin.test.Test
import kotlin.test.assertEquals

class ContextTest {
    @Test
    fun `test fluent interface`() {
        var ctx = Context.Builder()
            .withRequestId("123")
            .withStatus(IContextStatus.STARTED)
            .withRequestLocale(LocaleModel.RU)
            .withError(CommonError.fromCommon("error"))
            .build()

        assertEquals("123", ctx.requestId)
        assertEquals(1, ctx.errors.size)
        assertEquals(LocaleModel.RU, ctx.requestLocale)
    }
}
