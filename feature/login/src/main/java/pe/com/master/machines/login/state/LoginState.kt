package pe.com.master.machines.login.state

import pe.com.master.machines.model.model.Data

sealed interface LoginState {

    data object First : LoginState
    data object Loading : LoginState
    data class Error(val message: String) : LoginState
    data class SuccessLogin(val data: Data) : LoginState
}