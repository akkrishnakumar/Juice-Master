import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class ConsoleAppTest {

    companion object {

        private val consoleQueue = ArrayDeque<String>()
        private fun consoleOutputStream(output: String) = consoleQueue.addLast(output)

        private val consoleApp = DefaultConsoleApp(::consoleOutputStream)

        @BeforeAll
        @JvmStatic
        internal fun `Run Console App`() {
            consoleApp.run()

        }
    }


    @Test
    internal fun `Juice Master should initiate and return default status`() {
        val expectedStatus = defaultStatus

        val actualStatus = consoleQueue[0]

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
}