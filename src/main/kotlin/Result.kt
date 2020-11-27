interface Error
sealed class Result<out Error, out T>

class Success<T>(val value: T) : Result<Nothing, T>()
class Failure(val err: Error) : Result<Error, Nothing>()

fun <T, R> Result<Error, T>.map(transform: (T) -> R): Result<Error, R> = when (this) {
    is Success -> Success(transform(value))
    is Failure -> Failure(err)
}
