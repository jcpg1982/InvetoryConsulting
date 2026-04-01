package pe.com.master.machines.root_navigation.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pe.com.master.machines.common.Resource
import pe.com.master.machines.domain.firebase.usesCase.GetDeviceConfigsUsesCase
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(
    private val getDeviceConfigsUsesCase: GetDeviceConfigsUsesCase,
    @param:ApplicationContext private val context: Context
) : ViewModel() {

    private val _isDeviceAuthorized = MutableStateFlow<Boolean?>(null)
    val isDeviceAuthorized: StateFlow<Boolean?> = _isDeviceAuthorized.asStateFlow()

    private val _deviceId = MutableStateFlow("")
    val deviceId: StateFlow<String> = _deviceId.asStateFlow()

    init {
        checkDeviceAuthorization()
    }

    @SuppressLint("HardwareIds")
    private fun checkDeviceAuthorization() {
        _deviceId.update {
            Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.ANDROID_ID
            )
        }

        viewModelScope.launch {
            getDeviceConfigsUsesCase.invoke()
                .flowOn(Dispatchers.IO)
                .onStart { }
                .catch { }
                .collect { res ->
                    when (res) {
                        is Resource.Error -> {}
                        is Resource.Success -> {}
                    }
                    /*val deviceConfig = configs.find { it.deviceId == androidId }

                    if (deviceConfig != null && deviceConfig.isActive) {
                        _currentDeviceConfig.value = deviceConfig
                        _isDeviceAuthorized.value = true
                    } else {
                        _isDeviceAuthorized.value = false
                    }*/
                }
        }
    }
}
