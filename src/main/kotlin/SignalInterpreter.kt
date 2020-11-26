import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

typealias SignalInterpreter = (String) -> Result<Error, MotionDetected>

class DefaultSignalInterpreter : SignalInterpreter {

    private val mapper = jacksonObjectMapper()

    override fun invoke(signal: String): Result<Error, MotionDetected> =
        Success(mapper.readValue(signal))

}