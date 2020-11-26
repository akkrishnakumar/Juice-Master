import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test

class SignalInterpreterTest {

    val signalInterpreter = DefaultSignalInterpreter()

    @Test
    internal fun `should return success of instruction when valid raw signal is received`() {
        val expectedSignalObject = MotionDetected(1, 2)
        val inputSignal = sampleMotionSignal

        val actualSignal = signalInterpreter(inputSignal).isSuccess()

        assertThat(actualSignal, equalTo(expectedSignalObject))
    }

    @Test
    internal fun `should return failure when invalid raw signal is received`() {
        val expectedFailureOutput = InvalidSignalInput
        val inputSignal = "Some Gibberish"

        val actualOutput = signalInterpreter(inputSignal).isFailure()

        assertThat(actualOutput, equalTo(expectedFailureOutput))
    }

}