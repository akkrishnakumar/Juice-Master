import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource
import java.time.LocalTime
import java.time.LocalTime.MIDNIGHT

class NightShiftTest {

    val isNightShift = DefaultNightShift()

    @ParameterizedTest
    @MethodSource("nightShiftTimings")
    internal fun `should return true if in night shift (6PM to 6AM)`(time: LocalTime) {
        val expected = true

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
    }

}