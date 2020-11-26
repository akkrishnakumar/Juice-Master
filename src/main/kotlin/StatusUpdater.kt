typealias StatusUpdater = (Instruction) -> (Status) -> Status

class DefaultStatusUpdater : StatusUpdater {

    override fun invoke(instruction: Instruction): (Status) -> Status = when (instruction) {
        is MotionDetected -> updateStatusOnMotion(instruction)
    }

    private fun updateStatusOnMotion(signal: MotionDetected): (Status) -> Status = { oldStatus ->
        oldStatus.copy(floors = oldStatus.floors.map { it.updateFloorUsing(signal) })
    }

    private fun Floor.updateFloorUsing(signal: MotionDetected): Floor =
        if (signal.floor == number) updateCorridorUsing(signal) else this

    private fun Floor.updateCorridorUsing(signal: MotionDetected): Floor =
        this.copy(subCorridors = subCorridors.updateSubCorridorUsing(signal))

    private fun List<SubCorridor>.updateSubCorridorUsing(signal: MotionDetected): List<SubCorridor> = map {
        if (it.number == signal.number) it.copy(light = true) else it
    }
}
