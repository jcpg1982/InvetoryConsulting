package pe.com.master.machines.domain.network.usesCase

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.Resource
import pe.com.master.machines.model.request.RequestLoginUser
import pe.com.master.machines.model.response.ResponseLoginUser

interface LoginUserUsesCase {

    operator fun invoke(body: RequestLoginUser): Flow<Resource<ResponseLoginUser>>

}