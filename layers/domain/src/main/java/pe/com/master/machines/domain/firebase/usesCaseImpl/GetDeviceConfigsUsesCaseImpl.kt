package pe.com.master.machines.domain.firebase.usesCaseImpl

import pe.com.master.machines.data.firebase.repository.FirebaseDataRepository
import pe.com.master.machines.domain.firebase.usesCase.GetDeviceConfigsUsesCase
import javax.inject.Inject

class GetDeviceConfigsUsesCaseImpl @Inject constructor(
    private val firebaseDataRepository: FirebaseDataRepository
) : GetDeviceConfigsUsesCase {

    override fun invoke() = firebaseDataRepository.getDeviceConfigs()

}