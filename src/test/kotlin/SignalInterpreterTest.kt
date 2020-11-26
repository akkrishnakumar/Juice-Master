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

}