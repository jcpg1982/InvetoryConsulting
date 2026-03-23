package pe.com.master.machines.home.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pe.com.master.machines.domain.network.usesCase.SearchActiveUsesCase
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(
    private val searchActiveUsesCase: SearchActiveUsesCase
) : ViewModel() {
}