import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

typealias SignalInterpreter = (String) -> Result<Error, Signal>

class DefaultSignalInterpreter : SignalInterpreter {

    private val mapper = jacksonObjectMapper()

    override fun invoke(signal: String): Result<Error, Signal> =
        try {
            Success(mapper.readValue(signal))
        } catch (e: Exception) {
            Failure(InvalidSignalInput)
        }

}

object InvalidSignalInput : Error