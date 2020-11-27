sealed class ConsoleApp(val outputStream: (String) -> Unit) {
    fun printOutput(output: String) = outputStream(output)
}

class DefaultConsoleApp(
    outputStream: (String) -> Unit = ::consoleOutputStream,
    val isNightShift: NightShift = DefaultNightShift(),
    noMotionTimeout: Long
) : ConsoleApp(outputStream) {

    val juiceMaster = JuiceMaster(
        2,
        1,
        2,
        isNightShift = isNightShift,
        noMotionTimeout = noMotionTimeout
    )

    fun status() = printOutput(juiceMaster.status())

    fun consume(signal: String): Unit = juiceMaster.consume(signal)

}

fun consoleOutputStream(output: String) = println(output)