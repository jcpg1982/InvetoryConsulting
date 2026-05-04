package pe.com.master.machines.home.state

import pe.com.master.machines.model.model.ActivePda

sealed interface HomeState {
    data object Idle : HomeState
    data object Loading : HomeState
    data class Error(val message: String) : HomeState
    data class SuccessSearch(
        val data: ActivePda,
        val listChildren: List<ActivePda>,
        val father: ActivePda
    ) : HomeState
}