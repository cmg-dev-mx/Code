package mx.dev.cmg.android.code.ui.feature.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<MainSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onEvent(event: MainEvent) {
        viewModelScope.launch {
            when (event) {
                is MainEvent.OnLoad -> loadData()
                is MainEvent.NavigateToRemoteConfigList -> navigateToRemoteConfigList()
            }
        }
    }

    private suspend fun loadData() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        delay(2000)
        _uiState.value = _uiState.value.copy(name = "Code", isLoading = false)
    }

    private suspend fun navigateToRemoteConfigList() {
        _sideEffect.send(MainSideEffect.NavigateToRemoteConfigList)
    }
}