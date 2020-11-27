fun main(args: Array<String>) {
    val app = DefaultConsoleApp(noMotionTimeout = 60000)

    app.status()
    app.consume(signalFromFloor1sub2)
    app.status()

    // wait for more than a  min
    Thread.sleep(60000 + 50)

    app.status()

}

val signalFromFloor1sub2 = """
        {
          "floor": "1",
          "number": "2"
        }
    """.trimIndent()