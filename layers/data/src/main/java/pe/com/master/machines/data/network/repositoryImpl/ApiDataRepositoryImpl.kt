package pe.com.master.machines.data.network.repositoryImpl

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import pe.com.master.machines.common.Resource
import pe.com.master.machines.common.toErrorType
import pe.com.master.machines.data.mappers.asModelActivePda
import pe.com.master.machines.data.mappers.asModelRequestLoginUserNetwork
import pe.com.master.machines.data.mappers.asModelResponseLoginUser
import pe.com.master.machines.data.mappers.asModelResponseSearchBarcode
import pe.com.master.machines.data.network.repository.ApiDataRepository
import pe.com.master.machines.model.request.RequestLoginUser
import pe.com.master.machines.network.repository.ApiNetworkRepository
import javax.inject.Inject

class ApiDataRepositoryImpl @Inject constructor(
    private val apiNetworkRepository: ApiNetworkRepository
) : ApiDataRepository {

    override fun loginUser(body: RequestLoginUser) =
        apiNetworkRepository.loginUser(body.asModelRequestLoginUserNetwork()).map { res ->
            when (res) {
                is Resource.Success -> Resource.Success(res.data.asModelResponseLoginUser())
                is Resource.Error -> Resource.Error(res.errorType)
            }
        }.catch { e ->
            emit(Resource.Error(e.toErrorType()))
        }

    override fun buscarActivo(sociedadId: Int, invId: Int, barcode: String) =
        apiNetworkRepository.buscarActivo(sociedadId, invId, barcode).map { res ->
            when (res) {
                is Resource.Success -> Resource.Success(res.data.asModelResponseSearchBarcode())
                is Resource.Error -> Resource.Error(res.errorType)
            }
        }.catch { e ->
            emit(Resource.Error(e.toErrorType()))
        }
}
