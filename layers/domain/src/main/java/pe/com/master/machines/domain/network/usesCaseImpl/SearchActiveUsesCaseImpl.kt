package pe.com.master.machines.domain.network.usesCaseImpl

import pe.com.master.machines.data.network.repository.ApiDataRepository
import pe.com.master.machines.domain.network.usesCase.SearchActiveUsesCase
import javax.inject.Inject

class SearchActiveUsesCaseImpl @Inject constructor(
    private val apiDataRepository: ApiDataRepository
) : SearchActiveUsesCase {

    override fun invoke(userId: Int, invId: Int, barcode: String) =
        apiDataRepository.buscarActivo(userId, invId, barcode)
}