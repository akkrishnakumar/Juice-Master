import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test

class ConsoleAppTest {

    private val consoleQueue = ArrayDeque<String>()
    private fun consoleOutputStream(output: String) = consoleQueue.addLast(output)

    private val consoleApp = DefaultConsoleApp(
        outputStream = ::consoleOutputStream,
        isNightShift = { true }
    )

    @Test
    internal fun `Juice Master should initiate and return default status`() {
        val expectedStatus = defaultStatus

        consoleApp.status()
        val actualStatus = consoleQueue[0]

        assertThat(actualStatus, equalTo(expectedStatus))

    }

    @Test
    internal fun `Juice Master will receive motion sensor signal and turn on light accordingly`() {
        val expectedStatus = statusAfterMotionDetected

        val actualStatus = consoleApp.consume(sampleMotionSignal)

        assertThat(actualStatus, equalTo(expectedStatus))
    }

    val defaultStatus = """
        Floor 1
        Main corridor 1 Light 1 : ON AC : ON
        Sub corridor 1 Light 1 : OFF AC : ON
        Sub corridor 2 Light 2 : OFF AC : ON
        Floor 2
        Main corridor 1 Light 1 : ON AC : ON
        Sub corridor 1 Light 1 : OFF AC : ON
        Sub corridor 2 Light 2 : OFF AC : ON
    """.trimIndent()

    val statusAfterMotionDetected = """
        Floor 1
        Main corridor 1 Light 1 : ON AC : ON
        Sub corridor 1 Light 1 : OFF AC : OFF
        Sub corridor 2 Light 2 : ON AC : ON
        Floor 2
        Main corridor 1 Light 1 : ON AC : ON
        Sub corridor 1 Light 1 : OFF AC : ON
        Sub corridor 2 Light 2 : OFF AC : ON
    """.trimIndent()
}