package pe.com.master.machines.common

import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

fun Throwable.toErrorType(): ErrorType {
    val originalMessage = this.message
    val messageDetail = originalMessage?.takeIf { it.isNotBlank() }

    return when (this) {
        is SocketTimeoutException -> ErrorType.Api.Timeout(
            messageDetail ?: "Timeout: La solicitud tardó demasiado en responder."
        )

        is IOException -> ErrorType.Api.Network(
            messageDetail ?: "Error de Red: No se pudo establecer la conexión."
        )

        is HttpException -> {
            when (val responseStatus = this.code()) {
                ErrorCodes.Http.BAD_REQUEST -> ErrorType.Api.BadRequest(
                    messageDetail ?: "Error (400): Solicitud incorrecta."
                )

                ErrorCodes.Http.UNAUTHORIZED -> ErrorType.Api.Unauthorized(
                    messageDetail ?: "Error de Autenticación (401): No autorizado."
                )

                ErrorCodes.Http.INTERNAL_SERVER -> ErrorType.Api.Server(
                    messageDetail ?: "Error del Servidor (500): Problema interno."
                )

                ErrorCodes.Http.BAD_GATEWAY -> ErrorType.Api.BadGateway(
                    messageDetail
                        ?: "Error de Enlace (502): Problema de comunicación entre servidores."
                )

                ErrorCodes.Http.SERVICE_UNAVAILABLE -> ErrorType.Api.ServiceUnavailable(
                    messageDetail ?: "Error (503): Servicio no disponible temporalmente."
                )

                else -> ErrorType.Unknown(
                    messageDetail ?: "Error en respuesta del servidor (código: $responseStatus)."
                )
            }
        }

        else -> ErrorType.Unknown(
            messageDetail ?: "Ha ocurrido un error inesperado."
        )
    }
}
