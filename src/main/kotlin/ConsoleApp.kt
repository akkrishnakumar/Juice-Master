sealed class ConsoleApp(val outputStream: (String) -> Unit) {
    fun printOutput(output: String) = outputStream(output)
}

class DefaultConsoleApp(
    outputStream: (String) -> Unit = ::consoleOutputStream
) : ConsoleApp(outputStream) {

    fun run() {

        // Put Business Scenario here

        printOutput("Yet to be implemented")
    }

}

fun consoleOutputStream(output: String) = println(output)