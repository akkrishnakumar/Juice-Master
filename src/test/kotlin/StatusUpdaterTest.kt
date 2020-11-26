import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test

class StatusUpdaterTest {

    val statusUpdater = DefaultStatusUpdater()

    @Test
    internal fun `should turn on lights in sub corridor when motion is detected`() {
        val expectedStatus = sampleUpdatedStatus
        val initialStatus = defaultStatus()
        val inputSignal = sampleMotionDetectedSignal

        val updateFunc = statusUpdater(inputSignal)
        val actualStatus = updateFunc(initialStatus)

        assertThat(actualStatus, equalTo(expectedStatus))
    }

    private val sampleMotionDetectedSignal = MotionDetected(1, 2)

    private val sampleUpdatedStatus = Status(
        listOf(
            Floor(
                1,
                listOf(MainCorridor(1, true, true)),
                listOf(SubCorridor(1, false, true), SubCorridor(2, true, true))
            ),
            Floor(
                2,
                listOf(MainCorridor(1, true, true)),
                listOf(SubCorridor(1, false, true), SubCorridor(2, false, true))
            )
        )
    )

}