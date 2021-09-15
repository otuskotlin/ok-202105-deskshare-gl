import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.RequestContextStatus
import com.deskshare.common.context.command.CreateCommandRequest
import com.deskshare.common.context.command.DeleteCommandRequest
import com.deskshare.common.context.command.UpdateCommandRequest
import com.deskshare.common.context.query.FindByIdQueryRequest
import com.deskshare.common.models.ReservationIdModel
import com.deskshare.common.models.UserIdModel
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
    fun `update by id`() {
        val manager = ReservationManager()
        val ctx = RequestContext(
            requestId = "123",
            request = UpdateCommandRequest(
                requestModel = ReservationStub.getPendingModel()
            ),
            stubCase = true
        )

        runBlocking {
            manager.update(ctx)
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
    fun `delete by id`() {
        val manager = ReservationManager()
        val ctx = RequestContext(
            requestId = "123",
            request = DeleteCommandRequest(
                requestModelId = ReservationIdModel("123")
            ),
            stubCase = true
        )

        runBlocking {
            manager.delete(ctx)
        }

        val expected = ReservationStub.getCanceledModel()

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
    fun `find by id`() {
        val manager = ReservationManager()
        val ctx = RequestContext(
            requestId = "123",
            request = FindByIdQueryRequest(reservationId = ReservationIdModel("123")),
            stubCase = true
        )

        runBlocking {
            manager.findById(ctx)
        }

        val expected = ReservationStub.getModel()

        assertEquals(RequestContextStatus.Success, ctx.status)
        assertEquals(0, ctx.errors.size)
        assertEquals(listOf(expected), ctx.request.responseModels.toList())
    }
}
