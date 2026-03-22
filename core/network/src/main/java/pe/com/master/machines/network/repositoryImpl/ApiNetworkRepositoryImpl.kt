package pe.com.master.machines.network.repositoryImpl

import com.google.gson.Gson
import kotlinx.coroutines.flow.flow
import pe.com.master.machines.common.Resource
import pe.com.master.machines.common.toErrorType
import pe.com.master.machines.network.di.ApiService
import pe.com.master.machines.network.model.request.RequestLoginUserNetwork
import pe.com.master.machines.network.model.response.ResponseLoginError
import pe.com.master.machines.network.repository.ApiNetworkRepository
import javax.inject.Inject

class ApiNetworkRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ApiNetworkRepository {

    private val gson = Gson()

    override fun loginUser(body: RequestLoginUserNetwork) = flow {
        try {
            val response = apiService.loginUser(body)
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()))
            } else {
                val errorJson = response.errorBody()?.string()
                val errorResponse = try {
                    gson.fromJson(errorJson, ResponseLoginError::class.java)
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

    override fun buscarActivo(userId: Int, invId: Int, barcode: String) = flow {
        try {
            val response = apiService.buscarActivo(userId, invId, barcode)
            if (response.isSuccessful) {
                emit(Resource.Success(response.body()))
            } else {
                val errorJson = response.errorBody()?.string()
                val errorResponse = try {
                    gson.fromJson(errorJson, ResponseLoginError::class.java)
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
