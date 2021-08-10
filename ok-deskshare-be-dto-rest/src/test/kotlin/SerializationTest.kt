import com.deskshare.openapi.models.ReservationStatusDto
import com.deskshare.openapi.models.ViewReservationDto
import com.fasterxml.jackson.databind.ObjectMapper
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SerializationTest {
    val reservationDto = ViewReservationDto(
        id = "id",
        description = "description",
        userId = "123",
        workspaceId = "456",
        from = "2020-12-01 12:12:12",
        until = "2020-12-01 12:12:12",
        status = ReservationStatusDto.PENDING,
        createdAt = "2020-12-01 12:12:12"
    )
    private val om = ObjectMapper()

    @Test
    fun `serialize test`() {
        val json = om.writeValueAsString(reservationDto)

        assertTrue(json.contains(""""userId":"${reservationDto.userId}""""))
        assertTrue(json.contains(""""workspaceId":"${reservationDto.workspaceId}""""))
        assertTrue(json.contains(""""from":"${reservationDto.from}""""))
        assertTrue(json.contains(""""status":"${ReservationStatusDto.PENDING.value}""""))
    }

    @Test
    fun `deserialize test`() {
        val json = om.writeValueAsString(reservationDto)
        val deserialized = om.readValue(json, ViewReservationDto::class.java)

        assertEquals(ReservationStatusDto.PENDING, deserialized.status)
        assertEquals(reservationDto.id, deserialized.id)
        assertEquals(reservationDto.from, deserialized.from)
        assertEquals(reservationDto.until, deserialized.until)
    }
}
