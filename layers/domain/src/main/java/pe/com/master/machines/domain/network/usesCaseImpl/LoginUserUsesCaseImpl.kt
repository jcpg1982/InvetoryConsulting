package pe.com.master.machines.domain.network.usesCaseImpl

import pe.com.master.machines.data.network.repository.ApiDataRepository
import pe.com.master.machines.domain.network.usesCase.LoginUserUsesCase
import pe.com.master.machines.model.request.RequestLoginUser
import javax.inject.Inject

class LoginUserUsesCaseImpl @Inject constructor(
    private val apiDataRepository: ApiDataRepository
) : LoginUserUsesCase {

    override fun invoke(body: RequestLoginUser) = apiDataRepository.loginUser(body)

}
