package pe.com.master.machines.common

object ErrorCodes {

    object Http {
        const val BAD_REQUEST = 400
        const val UNAUTHORIZED = 401
        const val NOT_FOUND = 404
        const val INTERNAL_SERVER = 500
        const val BAD_GATEWAY = 502
        const val SERVICE_UNAVAILABLE = 503
    }
}
