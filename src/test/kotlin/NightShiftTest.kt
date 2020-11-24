import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource
import java.time.LocalTime
import java.time.LocalTime.MIDNIGHT
import java.time.LocalTime.NOON

class NightShiftTest {

    val isNightShift = DefaultNightShift()

    @ParameterizedTest
    @MethodSource("nightShiftTimings")
    internal fun `should return true if in night shift (6PM to 6AM)`(time: LocalTime) {
        val expected = true

        val actual = isNightShift(time)

        assertThat(actual, equalTo(expected))
    }

    @ParameterizedTest
    @MethodSource("morningShiftTimings")
    internal fun `should return false if in morning shift`(time: LocalTime) {
        val expected = false

        val actual = isNightShift(time)

        assertThat(actual, equalTo(expected))
    }

    companion object {

        @JvmStatic
        fun nightShiftTimings() = listOf(
            of(MIDNIGHT),
            of(LocalTime.parse("18:00:01")),
            of(LocalTime.parse("05:59:59"))
        )

        @JvmStatic
        fun morningShiftTimings() = listOf(
            of(NOON),
            of(LocalTime.parse("17:59:59")),
            of(LocalTime.parse("06:00:01"))
        )
    }

}