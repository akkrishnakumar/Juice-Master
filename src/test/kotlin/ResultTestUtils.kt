import kotlin.test.fail

fun <T> Result<Error, T>.isSuccess() = when (this) {
    is Success -> this.value
    else       -> fail("Expected a success but got failure !")
}