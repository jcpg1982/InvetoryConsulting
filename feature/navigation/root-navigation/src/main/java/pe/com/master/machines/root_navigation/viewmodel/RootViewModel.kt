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
import pe.com.master.machines.common.ConstantsSystemProperties.versionCodeDevice
import pe.com.master.machines.common.Resource
import pe.com.master.machines.domain.firebase.usesCase.GetDeviceConfigsUsesCase
import pe.com.master.machines.model.model.RemoteDeviceConfig
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(
    private val getDeviceConfigsUsesCase: GetDeviceConfigsUsesCase,
    @param:ApplicationContext private val context: Context
) : ViewModel() {

    private val _isDeviceAuthorized = MutableStateFlow<Boolean?>(null)
    val isDeviceAuthorized: StateFlow<Boolean?> = _isDeviceAuthorized.asStateFlow()

    private val _mustUpdate = MutableStateFlow(false)
    val mustUpdate: StateFlow<Boolean> = _mustUpdate.asStateFlow()

    private val _isForceUpdate = MutableStateFlow(false)
    val isForceUpdate: StateFlow<Boolean> = _isForceUpdate.asStateFlow()

    private val _updateIgnored = MutableStateFlow(false)
    val updateIgnored: StateFlow<Boolean> = _updateIgnored.asStateFlow()

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
                .catch {
                    _isDeviceAuthorized.update { false }
                }
                .collect { res ->
                    when (res) {
                        is Resource.Error -> {
                            _isDeviceAuthorized.update { false }
                        }

                        is Resource.Success -> {
                            val deviceConfig = res.data.find { it.deviceId == deviceId.value }
                            val authorized = deviceConfig != null && deviceConfig.isActive == true
                            _isDeviceAuthorized.update { authorized }

                            if (authorized) {
                                checkUpdateStatus(deviceConfig)
                            }
                        }
                    }
                }
        }
    }

    private fun checkUpdateStatus(config: RemoteDeviceConfig) {
        try {
            val remoteMajor = config.versionMajor
            val remoteMinor = config.versionMinor

            val hasNewVersion =
                (remoteMajor > versionCodeDevice) || (remoteMinor > versionCodeDevice)

            if (hasNewVersion) {
                _mustUpdate.update { true }
                _isForceUpdate.update { config.forceUpdate }
            } else {
                _mustUpdate.update { false }
            }
        } catch (e: Exception) {
            _mustUpdate.update { false }
        }
    }

    fun ignoreUpdate() {
        _updateIgnored.update { true }
    }
}
