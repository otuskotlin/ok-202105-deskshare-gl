package dsl.user

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.TemporalAdjusters

@UserDsl
class UserAvailableContext {
    private val _availableList: MutableList<LocalDateTime> = mutableListOf()

    val availableList: List<LocalDateTime>
        get() = _availableList.toList()

    fun monday(time: String) = dayAndTimeOfWeek(DayOfWeek.MONDAY, time)
    fun tuesday(time: String) = dayAndTimeOfWeek(DayOfWeek.TUESDAY, time)
    fun wednesday(time: String) = dayAndTimeOfWeek(DayOfWeek.WEDNESDAY, time)
    fun thursday(time: String) = dayAndTimeOfWeek(DayOfWeek.THURSDAY, time)
    fun friday(time: String) = dayAndTimeOfWeek(DayOfWeek.FRIDAY, time)
    fun saturday(time: String) = dayAndTimeOfWeek(DayOfWeek.SATURDAY, time)
    fun sunday(time: String) = dayAndTimeOfWeek(DayOfWeek.SUNDAY, time)

    private fun dayAndTimeOfWeek(day: DayOfWeek, time: String) {
        _availableList.add(
            LocalDateTime.of(
                dayOfWeek(day),
                parseTime(time)
            )
        )
    }

    private fun dayOfWeek(day: DayOfWeek): LocalDate {
        return LocalDate.now().with(TemporalAdjusters.next(day))
    }

    private fun parseTime(time: String): LocalTime {
        return time.split(":")
            .map { it.toInt() }
            .let { LocalTime.of(it[0], it[1]) }
    }
}
