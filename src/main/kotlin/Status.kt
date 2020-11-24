data class Status(val floors: List<Floor>)
data class Floor(val number: Int, val mainCorridors: List<MainCorridor>, val subCorridors: List<SubCorridor>)

interface Corridor {
    val number: Int
    val light: Boolean
    val ac: Boolean
}

data class MainCorridor(override val number: Int, override val light: Boolean, override val ac: Boolean) : Corridor
data class SubCorridor(override val number: Int, override val light: Boolean, override val ac: Boolean) : Corridor