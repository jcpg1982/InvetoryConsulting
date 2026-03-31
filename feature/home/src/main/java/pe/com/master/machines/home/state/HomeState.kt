package pe.com.master.machines.home.state

import pe.com.master.machines.model.model.Data

sealed interface HomeState {

    data object First : HomeState
    data object Loading : HomeState
    data class Error(val message: String) : HomeState
    data object SuccessSearch : HomeState
}