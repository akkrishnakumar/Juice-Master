data class Status(val floors: List<Floor>)
data class Floor(val number: Int, val mainCorridors: List<MainCorridor>, val subCorridors: List<SubCorridor>)

interface Corridor {
    val number: Int
    val light: Boolean
    val ac: Boolean
}

data class MainCorridor(override val number: Int, override val light: Boolean, override val ac: Boolean) : Corridor
data class SubCorridor(override val number: Int, override val light: Boolean, override val ac: Boolean) : Corridor

fun Status.toHumanReadable(): String =
    floors.joinToString("\n") { it.toHumanReadable() }

private fun Floor.toHumanReadable(): String =
    """
        |Floor $number
        |${mainCorridors.toHumanReadable()}
        |${subCorridors.toHumanReadable()}
    """.trimMargin()

private fun List<Corridor>.toHumanReadable(): String = joinToString("\n") {
    "${it.getType()} corridor ${it.number} Light ${it.number} : ${it.light.toSwitchValue()} AC : ${it.ac.toSwitchValue()}"
}.trim()

private fun Corridor.getType(): String = when (this) {
    is MainCorridor -> "Main"
    else            -> "Sub"
}

private fun Boolean.toSwitchValue(): String = when (this) {
    true -> "ON"
    false -> "OFF"
}