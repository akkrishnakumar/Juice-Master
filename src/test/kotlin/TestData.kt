val sampleMotionSignal = """
        {
          "floor": "1",
          "number": "2"
        }
    """.trimIndent()

fun defaultStatus() = Status(
    listOf(
        Floor(
            1,
            listOf(MainCorridor(1, true, true)),
            listOf(SubCorridor(1, false, true), SubCorridor(2, false, true))
        ),
        Floor(
            2,
            listOf(MainCorridor(1, true, true)),
            listOf(SubCorridor(1, false, true), SubCorridor(2, false, true))
        )
    )
)