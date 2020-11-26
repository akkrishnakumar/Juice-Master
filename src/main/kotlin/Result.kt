interface Error
sealed class Result<out Error, out T>

class Success<T>(val value: T) : Result<Nothing, T>()
class Failure(val err: Error) : Result<Error, Nothing>()
