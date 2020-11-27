sealed class Instruction

data class MotionDetected(val floor: Int, val number: Int, val status: Status) : Instruction()
data class NoMotionDetected(val floor: Int, val number: Int, val status: Status) : Instruction()