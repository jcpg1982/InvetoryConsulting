package pe.com.master.machines.login.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pe.com.master.machines.common.Resource
import pe.com.master.machines.common.stringErrorMessage
import pe.com.master.machines.domain.network.usesCase.LoginUserUsesCase
import pe.com.master.machines.login.state.LoginState
import pe.com.master.machines.model.request.RequestLoginUser
import javax.inject.Inject

@HiltViewModel
class LoginViewmodel @Inject constructor(
    private val loginUserUsesCase: LoginUserUsesCase
) : ViewModel() {

    private val TAG = LoginViewmodel::class.java.simpleName

    private var _loginUserState = MutableStateFlow<LoginState>(LoginState.First)
    val loginUserState get() = _loginUserState.asStateFlow()

    private var _documentNumber = MutableStateFlow("07198878")
    val documentNumber get() = _documentNumber.asStateFlow()

    fun updateDocumentNumber(value: String) {
        _documentNumber.update { value }
    }

    fun getLoginUser() {
        Log.d(TAG, "getLoginUser: Iniciando proceso de login con datos: ${documentNumber.value}")
        if (documentNumber.value.isBlank()) {
            return
        }
        viewModelScope.launch {
            loginUserUsesCase.invoke(RequestLoginUser(documentNumber.value))
                .flowOn(Dispatchers.IO)
                .onStart {
                    Log.d(TAG, "getLoginUser: onStart - Actualizando a estado Loading")
                    _loginUserState.update { LoginState.Loading }
                }
                .catch { e ->
                    Log.e(TAG, "getLoginUser: catch - Error capturado: ${e.message}", e)
                    _loginUserState.update { LoginState.Error(e.message ?: "Error desconocido") }
                }
                .collect { res ->
                    when (res) {
                        is Resource.Error -> {
                            Log.e(
                                TAG,
                                "getLoginUser: Resource.Error - Mensaje: ${res.stringErrorMessage}"
                            )
                            _loginUserState.update { LoginState.Error(res.stringErrorMessage) }
                        }

                        is Resource.Success -> {
                            Log.d(TAG, "getLoginUser: Resource.Success - Login exitoso")
                            if (res.data.ok) {
                                _loginUserState.update { LoginState.SuccessLogin(res.data.data) }
                            } else {
                                _loginUserState.update { LoginState.Error(res.data.message) }
                            }
                        }
                    }
                }
        }
    }

    fun resetLoginState() {
        _loginUserState.update { LoginState.First }
    }


}