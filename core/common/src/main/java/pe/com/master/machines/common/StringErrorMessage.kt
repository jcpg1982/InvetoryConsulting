package pe.com.master.machines.common

val <T> Resource.Error<T>.stringErrorMessage
    get() = run {
        when (val error = this.errorType) {
            is ErrorType.Api.Network -> error.message
            is ErrorType.Api.BadRequest -> error.message
            is ErrorType.Api.Server -> error.message
            is ErrorType.Api.ServiceUnavailable -> error.message
            is ErrorType.Api.Timeout -> error.message
            is ErrorType.Api.Unauthorized -> error.message
            is ErrorType.Unknown -> error.message
            is ErrorType.Api.BadGateway -> error.message
        }
    }