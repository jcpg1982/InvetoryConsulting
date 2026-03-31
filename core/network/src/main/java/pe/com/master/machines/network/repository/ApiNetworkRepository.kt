package pe.com.master.machines.network.repository

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.Resource
import pe.com.master.machines.network.model.request.RequestLoginUserNetwork
import pe.com.master.machines.network.model.response.ResponseLoginUserNetwork
import pe.com.master.machines.network.model.response.ResponseSearchBarcodeNetwork

interface ApiNetworkRepository {

    fun loginUser(body: RequestLoginUserNetwork): Flow<Resource<ResponseLoginUserNetwork?>>
    fun buscarActivo(sociedadId: Int, invId: Int, barcode: String): Flow<Resource<ResponseSearchBarcodeNetwork?>>
}