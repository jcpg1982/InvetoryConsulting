package pe.com.master.machines.network.di

import pe.com.master.machines.network.model.request.RequestLoginUserNetwork
import pe.com.master.machines.network.model.response.ResponseLoginUserNetwork
import pe.com.master.machines.network.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST(Constants.LOGIN_USER)
    suspend fun loginUser(@Body body: RequestLoginUserNetwork): Response<ResponseLoginUserNetwork>

    @GET(Constants.SEARCH_ACTIVE)
    suspend fun buscarActivo(
        @Path("id_sociedad") sociedadId: Int,
        @Path("id_inventario") invId: Int,
        @Path("cod_barra") barcode: String
    ): Response<ActivePdaNetwork>

}