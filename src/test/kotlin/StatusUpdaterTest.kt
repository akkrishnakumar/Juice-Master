import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test

class StatusUpdaterTest {

    @Test
    internal fun `should turn on lights in sub corridor when motion is detected`() {
        val expectedStatus = sampleUpdatedStatus
        val initialStatus = defaultStatus()
        val inputSignal = sampleMotionDetectedSignal


        val actualStatus = TODO("Need to implement Status Updater")

        assertThat(actualStatus, equalTo(expectedStatus))
    }

    private val sampleMotionDetectedSignal = MotionDetected(1, 2)

    private val sampleUpdatedStatus = """
        Floor 1
        Main corridor 1 Light 1 : ON AC : ON
        Sub corridor 1 Light 1 : OFF AC : ON
        Sub corridor 2 Light 2 : ON AC : ON
        Floor 2
        Main corridor 1 Light 1 : ON AC : ON
        Sub corridor 1 Light 1 : OFF AC : ON
        Sub corridor 2 Light 2 : OFF AC : ON
    """.trimIndent()

}