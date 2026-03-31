package pe.com.master.machines.network.repositoryImpl

import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import pe.com.master.machines.common.Resource
import pe.com.master.machines.common.toErrorType
import pe.com.master.machines.network.di.ApiService
import pe.com.master.machines.network.model.request.RequestLoginUserNetwork
import pe.com.master.machines.network.model.response.ResponseLoginError
import pe.com.master.machines.network.repository.ApiNetworkRepository
import javax.inject.Inject

class ApiNetworkRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val json: Json
) : ApiNetworkRepository {

    override fun loginUser(body: RequestLoginUserNetwork) = flow {
        try {
            val response = apiService.loginUser(body)
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()))
            } else {
                val errorJson = response.errorBody()?.string()
                val errorResponse = try {
                    errorJson?.let { json.decodeFromString<ResponseLoginError>(it) }
                } catch (e: Exception) {
                    null
                }
                val message = errorResponse?.message ?: response.message()
                emit(Resource.Error(Throwable(message).toErrorType()))

            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toErrorType()))
        }
    }

    override fun buscarActivo(sociedadId: Int, invId: Int, barcode: String) = flow {
        try {
            val response = apiService.buscarActivo(sociedadId, invId, barcode)
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()))
            } else {
                val errorJson = response.errorBody()?.string()
                val errorResponse = try {
                    errorJson?.let { json.decodeFromString<ResponseLoginError>(it) }
                } catch (e: Exception) {
                    null
                }
                val message = errorResponse?.message ?: response.message()
                emit(Resource.Error(Throwable(message).toErrorType()))

            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toErrorType()))
        }
    }
}
