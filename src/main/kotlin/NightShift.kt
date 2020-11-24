import java.time.LocalTime

typealias NightShift = (LocalTime) -> Boolean

class DefaultNightShift(
    val from: LocalTime = LocalTime.parse("18:00:00"),
    val to: LocalTime = LocalTime.parse("06:00:00")
) : NightShift {

    override fun invoke(time: LocalTime): Boolean =
        from.isAfter(time) || to.isBefore(time)

}


