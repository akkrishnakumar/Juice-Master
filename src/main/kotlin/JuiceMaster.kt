import java.time.LocalTime.now
import java.util.*
import kotlin.concurrent.timerTask

class JuiceMaster(
    val floors: Int,
    val mainCorridorsPerFloor: Int,
    val subCorridorsPerFloor: Int,
    val noMotionTimeout: Long = 60000,
    val isNightShift: NightShift = DefaultNightShift(),
    val signalInterpreter: SignalInterpreter = DefaultSignalInterpreter(),
    val instructionProcessor: InstructionProcessor = DefaultInstructionProcessor()
) {

    private var status = initialize()
    private val timers: MutableList<Timer> = mutableListOf()

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
            MainCorridor(it, light = isNightShift(now()), ac = true)
        }

    private fun initializeDefaultSubCorridorUsing(subCorridorsPerFloor: Int): List<SubCorridor> =
        (1..subCorridorsPerFloor).map {
            SubCorridor(it, light = false, ac = true)
        }

    fun consume(signal: String) = signalInterpreter(signal)
        .map { it.processSignal() }
        .orElse { it.handleError() }

    private fun Signal.processSignal() {
        terminateTimerAt(floor)
        status = instructionProcessor(MotionDetected(floor, number, status))
        setNoMotionTimer(this)
    }

    private fun terminateTimerAt(floor: Int) {
        if (timers.isNotEmpty()) {
            val timer = timers[floor]
            timer.cancel()
            timers.removeAt(floor)
        }
    }

    private fun setNoMotionTimer(signal: Signal) {
        val timer = Timer("Floor ${signal.floor}", false)
        val task = timerTask {
            status = instructionProcessor(NoMotionDetected(signal.floor, signal.number, status))
            cancel()
            timer.cancel()
        }
        timer.schedule(task, noMotionTimeout)
    }

    private fun Error.handleError() = when (this) {
        InvalidSignalInput -> Unit // Do your side effecting logs here.
        else               -> Unit
    }

}
