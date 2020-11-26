import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

typealias SignalInterpreter = (String) -> MotionDetected

class DefaultSignalInterpreter : SignalInterpreter {

    private val mapper = jacksonObjectMapper()

    override fun invoke(signal: String): MotionDetected = mapper.readValue(signal)

}