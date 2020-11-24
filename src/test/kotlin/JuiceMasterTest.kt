import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test

class JuiceMasterTest {

    val juiceMaster = JuiceMaster(2, 1, 2)

    @Test
    internal fun `should switch on all main corridor light during night shift`() {
        val expectedStatus = sampleDefaultStatusAtNightShift

        val actualStatus = juiceMaster.status()

        assertThat(actualStatus, equalTo(expectedStatus))
    }

    @Test
    internal fun `should switch off all main corridor light during morning shift`() {
        val expectedStatus = sampleDefaultStatusAtMorningShift

        val actualStatus = juiceMaster.status()

        assertThat(actualStatus, equalTo(expectedStatus))
    }

    val sampleDefaultStatusAtNightShift = """
        Floor 1
        Main corridor 1 Light 1 : ON AC : ON
        Sub corridor 1 Light 1 : OFF AC : ON
        Sub corridor 2 Light 2 : OFF AC : ON
        Floor 2
        Main corridor 1 Light 1 : ON AC : ON
        Sub corridor 1 Light 1 : OFF AC : ON
        Sub corridor 2 Light 2 : OFF AC : ON
    """.trimIndent()

    val sampleDefaultStatusAtMorningShift = """
        Floor 1
        Main corridor 1 Light 1 : OFF AC : ON
        Sub corridor 1 Light 1 : OFF AC : ON
        Sub corridor 2 Light 2 : OFF AC : ON
        Floor 2
        Main corridor 1 Light 1 : OFF AC : ON
        Sub corridor 1 Light 1 : OFF AC : ON
        Sub corridor 2 Light 2 : OFF AC : ON
    """.trimIndent()


}