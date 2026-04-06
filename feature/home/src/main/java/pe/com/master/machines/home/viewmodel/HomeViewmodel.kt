package pe.com.master.machines.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pe.com.master.machines.common.Resource
import pe.com.master.machines.common.stringErrorMessage
import pe.com.master.machines.domain.network.usesCase.SearchActiveUsesCase
import pe.com.master.machines.home.state.HomeState
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(
    private val searchActiveUsesCase: SearchActiveUsesCase
) : ViewModel() {

    private val TAG = HomeViewmodel::class.java.simpleName

    private var _homeState = Channel<HomeState>()
    val homeState get() = _homeState.receiveAsFlow()

    fun getSearchActivePda(sociedadId: Int, invId: Int, barcode: String) {
        Log.d(
            TAG,
            "getSearchActivePda: Iniciando proceso sociedadId: $sociedadId, invId: $invId, barcode: $barcode"
        )
        viewModelScope.launch {
            searchActiveUsesCase.invoke(sociedadId, invId, barcode)
                .flowOn(Dispatchers.IO)
                .onStart {
                    Log.d(TAG, "getSearchActivePda: onStart - Actualizando a estado Loading")
                    _homeState.send(HomeState.Loading)
                }
                .catch { e ->
                    Log.e(TAG, "getSearchActivePda: catch - Error capturado: ${e.message}", e)
                    _homeState.send(HomeState.Error(e.message ?: "Error desconocido"))
                }
                .collect { res ->
                    when (res) {
                        is Resource.Error -> {
                            Log.e(
                                TAG,
                                "getSearchActivePda: Resource.Error - Mensaje: ${res.stringErrorMessage}"
                            )
                            _homeState.send(HomeState.Error(res.stringErrorMessage))
                        }

                        is Resource.Success -> {
                            Log.d(TAG, "getSearchActivePda: Resource.Success - Login exitoso")
                            _homeState.send(
                                HomeState.SuccessSearch(
                                    res.data.data,
                                    res.data.listChildren
                                )
                            )
                        }
                    }
                }
        }
    }
}