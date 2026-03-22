package pe.com.master.machines.network.repository

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.Resource
import pe.com.master.machines.network.model.model.ActivePdaNetwork
import pe.com.master.machines.network.model.request.RequestLoginUserNetwork
import pe.com.master.machines.network.model.response.ResponseLoginUserNetwork
import retrofit2.Response
import retrofit2.http.Path

interface ApiNetworkRepository {

    fun loginUser(body: RequestLoginUserNetwork): Flow<Resource<ResponseLoginUserNetwork?>>
    fun buscarActivo(userId: Int, invId: Int, barcode: String): Flow<Resource<ActivePdaNetwork?>>
}