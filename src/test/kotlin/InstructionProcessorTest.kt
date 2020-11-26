import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test

class InstructionProcessorTest {

    val statusUpdater = DefaultInstructionProcessor()

    @Test
    internal fun `should turn on lights in sub corridor when motion is detected`() {
        // TODO: Need to change test data where budgeting is not applied
        val expectedStatus = sampleUpdatedStatus
        val initialStatus = defaultStatus()
        val inputSignal = MotionDetected(1, 2, initialStatus)

        val actualStatus = statusUpdater(inputSignal)

        assertThat(actualStatus, equalTo(expectedStatus))
    }

    @Test
    internal fun `should turn off all ACs on adjacent sub corridors if consumption goes out of budget`() {
        val expectedStatus = sampleBudgetedStatus
        val initialStatus = defaultStatus()
        val inputSignal = MotionDetected(1, 2, initialStatus)

        val actualStatus = statusUpdater(inputSignal)

        assertThat(actualStatus, equalTo(expectedStatus))
    }

    private val sampleUpdatedStatus = Status(
        listOf(
            Floor(
                1,
                listOf(MainCorridor(1, true, true)),
                listOf(SubCorridor(1, false, false), SubCorridor(2, true, true))
            ),
            Floor(
                2,
                listOf(MainCorridor(1, true, true)),
                listOf(SubCorridor(1, false, true), SubCorridor(2, false, true))
            )
        )
    )

    val sampleBudgetedStatus = Status(
        listOf(
            Floor(
                1,
                listOf(MainCorridor(1, true, true)),
                listOf(SubCorridor(1, false, false), SubCorridor(2, true, true))
            ),
            Floor(
                2,
                listOf(MainCorridor(1, true, true)),
                listOf(SubCorridor(1, false, true), SubCorridor(2, false, true))
            )
        )
    )

}