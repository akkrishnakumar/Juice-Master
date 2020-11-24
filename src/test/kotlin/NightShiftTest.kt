import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test
import java.time.LocalTime.MIDNIGHT

class NightShiftTest {

    val isNightShift = DefaultNightShift()

    @Test
    internal fun `should return true if in night shift (6PM to 6AM)`() {
        val expected = true

        val actual = isNightShift(MIDNIGHT)

        assertThat(actual, equalTo(expected))
    }

}