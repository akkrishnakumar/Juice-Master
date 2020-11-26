typealias InstructionProcessor = (Instruction) -> Status

class DefaultInstructionProcessor : InstructionProcessor {

    override fun invoke(instruction: Instruction): Status = when (instruction) {
        is MotionDetected -> updateStatusOnMotion(instruction)
    }

    private fun updateStatusOnMotion(signal: MotionDetected): Status =
        signal.status.copy(floors = signal.status.floors.map { it.updateFloorUsing(signal) })
            .run { budgeting(signal.floor, signal.number) }

    private fun Floor.updateFloorUsing(signal: MotionDetected): Floor =
        if (signal.floor == number) updateCorridorUsing(signal) else this

    private fun Floor.updateCorridorUsing(signal: MotionDetected): Floor =
        this.copy(subCorridors = subCorridors.updateSubCorridorUsing(signal))

    private fun List<SubCorridor>.updateSubCorridorUsing(signal: MotionDetected): List<SubCorridor> = map {
        if (it.number == signal.number) it.copy(light = true) else it
    }

    private fun Status.budgeting(floor: Int, number: Int): Status =
        copy(floors = floors.map {
            if (it.number == floor && it.exceedsBudget()) it.switchOffACs(number) else it
        })

    private fun Floor.exceedsBudget(): Boolean {
        val budget = (mainCorridors.size * 15) + (subCorridors.size * 10)
        val totalConsumption = mainCorridors.calculateConsumption() + subCorridors.calculateConsumption()
        return budget < totalConsumption
    }

    private fun Floor.switchOffACs(except: Int): Floor = copy(subCorridors = subCorridors.map {
        if (it.number != except) it.copy(ac = false) else it
    })

    private fun List<Corridor>.calculateConsumption(): Int = fold(0) { total, corridor ->
        total + unitsForLights(corridor.light) + unitsForAC(corridor.ac)
    }

    private fun unitsForLights(switch: Boolean) = if (switch) 5 else 0

    private fun unitsForAC(switch: Boolean) = if (switch) 10 else 0
}

