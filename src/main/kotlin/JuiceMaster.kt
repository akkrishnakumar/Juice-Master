class JuiceMaster(
    val floors: Int,
    val mainCorridorsPerFloor: Int,
    val subCorridorsPerFloor: Int,
) {

    private var status = initialize()

    fun status(): String = status.toHumanReadable()

    private fun initialize() = Status(
        (1..floors).map(initializeDefaultFloorStatusUsing(mainCorridorsPerFloor, subCorridorsPerFloor))
    )

    private fun initializeDefaultFloorStatusUsing(
        mainCorridorsPerFloor: Int,
        subCorridorsPerFloor: Int
    ): (Int) -> Floor = {
        Floor(
            it,
            initializeDefaultMainCorridorUsing(mainCorridorsPerFloor),
            initializeDefaultSubCorridorUsing(subCorridorsPerFloor)
        )
    }

    private fun initializeDefaultMainCorridorUsing(mainCorridorsPerFloor: Int): List<MainCorridor> =
        (1..mainCorridorsPerFloor).map {
            MainCorridor(it, light = true, ac = true)
        }

    private fun initializeDefaultSubCorridorUsing(subCorridorsPerFloor: Int): List<SubCorridor> =
        (1..subCorridorsPerFloor).map {
            SubCorridor(it, light = false, ac = true)
        }

}