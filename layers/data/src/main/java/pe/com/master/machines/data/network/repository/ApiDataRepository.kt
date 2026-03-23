package pe.com.master.machines.data.network.repository

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.Resource
import pe.com.master.machines.model.model.ActivePda
import pe.com.master.machines.model.request.RequestLoginUser
import pe.com.master.machines.model.response.ResponseLoginUser
import pe.com.master.machines.network.model.model.ActivePdaNetwork
import pe.com.master.machines.network.model.request.RequestLoginUserNetwork
import pe.com.master.machines.network.model.response.ResponseLoginUserNetwork

interface ApiDataRepository {

    fun loginUser(body: RequestLoginUser): Flow<Resource<ResponseLoginUser>>
    fun buscarActivo(userId: Int, invId: Int, barcode: String): Flow<Resource<ActivePda>>

}