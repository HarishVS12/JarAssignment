package com.harish.jarassignment.core.util

sealed class DomainException(
    val error: DomainError,
    message: String? = null,
    cause: Throwable? = null
) : Exception(message, cause) {

    class NetworkException(cause: Throwable? = null) :
        DomainException(DomainError.Network, "Network error", cause)

    class TimeoutException(cause: Throwable? = null) :
        DomainException(DomainError.Timeout, "Request timed out", cause)

    class NotFoundException(cause: Throwable? = null) :
        DomainException(DomainError.NotFound, "Not found", cause)

    class UnauthorizedException(cause: Throwable? = null) :
        DomainException(DomainError.Unauthorized, "Unauthorized", cause)

    class UnknownException(message: String?, cause: Throwable? = null) :
        DomainException(DomainError.Unknown(message), message, cause)

}


sealed class DomainError {
    object Network : DomainError()
    object Timeout : DomainError()
    object NotFound : DomainError()
    object Unauthorized : DomainError()
    data class Unknown(val cause: String?) : DomainError()
}