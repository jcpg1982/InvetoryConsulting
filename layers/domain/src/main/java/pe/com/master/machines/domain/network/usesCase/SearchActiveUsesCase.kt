package pe.com.master.machines.domain.network.usesCase

import kotlinx.coroutines.flow.Flow
import pe.com.master.machines.common.Resource
import pe.com.master.machines.model.model.ActivePda

interface SearchActiveUsesCase {

    operator fun invoke(sociedadId: Int, invId: Int, barcode: String): Flow<Resource<ActivePda>>

}