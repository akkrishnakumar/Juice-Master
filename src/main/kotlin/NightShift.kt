import java.time.LocalTime

typealias NightShift = (LocalTime) -> Boolean

class DefaultNightShift(
    val start: LocalTime = LocalTime.parse("18:00:00"),
    val end: LocalTime = LocalTime.parse("06:00:00")
) : NightShift {

    override fun invoke(time: LocalTime): Boolean =
        start.isBefore(time) || end.isAfter(time)

}


