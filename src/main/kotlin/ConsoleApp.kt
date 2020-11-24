sealed class ConsoleApp(val outputStream: (String) -> Unit) {
    fun printOutput(output: String) = outputStream(output)
}

class DefaultConsoleApp(
    outputStream: (String) -> Unit = ::consoleOutputStream,
    val isNightShift: NightShift = DefaultNightShift()
) : ConsoleApp(outputStream) {

    val juiceMaster = JuiceMaster(
        2,
        1,
        2,
        isNightShift = isNightShift
    )

    fun run() {
        // Put Business Scenario here

        // During Night Shift
        // 1. Initialize and print default status
        printOutput(juiceMaster.status())
    }

}

fun consoleOutputStream(output: String) = println(output)