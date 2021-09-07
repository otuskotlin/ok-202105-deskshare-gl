import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.RequestContextStatus
import com.deskshare.common.context.command.CreateCommandRequest
import com.deskshare.common.models.ReservationIdModel
import com.deskshare.logics.ReservationManager
import com.deskshare.stubs.ReservationStub
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class LogicsTest {
    @Test
    fun `create logic`() {
        val manager = ReservationManager()
        val ctx = RequestContext(
            requestId = "123",
            request = CreateCommandRequest(
                requestModel = ReservationStub.getModel().copy(id = ReservationIdModel.NONE)
            ),
            stubCase = true
        )

        runBlocking {
            manager.create(ctx)
        }

        val expected = ReservationStub.getPendingModel()

        assertEquals(RequestContextStatus.Success, ctx.status)
        assertEquals(0, ctx.errors.size)

        with(ctx.request.responseModel) {
            assertEquals(expected.id, id)
            assertEquals(expected.status, status)
            assertEquals(expected.description, description)
            assertEquals(expected.from, from)
            assertEquals(expected.until, until)
            assertEquals(expected.userId, userId)
            assertEquals(expected.workspaceId, workspaceId)
        }
    }

    @Test
    fun `create update by id`() {
        val manager = ReservationManager()
        val ctx = RequestContext(
            requestId = "123",
            request = CreateCommandRequest(
                requestModel = ReservationStub.getModel().copy(id = ReservationIdModel.NONE)
            ),
            stubCase = true
        )

        runBlocking {
            manager.create(ctx)
        }

        val expected = ReservationStub.getPendingModel()

        assertEquals(RequestContextStatus.Success, ctx.status)
        assertEquals(0, ctx.errors.size)

        with(ctx.request.responseModel) {
            assertEquals(expected.id, id)
            assertEquals(expected.status, status)
            assertEquals(expected.description, description)
            assertEquals(expected.from, from)
            assertEquals(expected.until, until)
            assertEquals(expected.userId, userId)
            assertEquals(expected.workspaceId, workspaceId)
        }
    }
}
