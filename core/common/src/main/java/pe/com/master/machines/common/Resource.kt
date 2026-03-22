package pe.com.master.machines.common

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val errorType: ErrorType) : Resource<T>()
}