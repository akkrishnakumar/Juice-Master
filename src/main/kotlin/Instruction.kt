sealed class Instruction

data class MotionDetected(val floor: Int, val number: Int) : Instruction()
