package mx.dev.cmg.android.code.ui.feature.remoteconfig.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import mx.dev.cmg.android.code.core.model.RemoteConfigItem

class RemoteConfigListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RemoteConfigListUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<RemoteConfigListSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    init {
        viewModelScope.launch {
            loadData()
        }
    }

    fun onEvent(event: RemoteConfigListEvent) {
        viewModelScope.launch {
            when (event) {
                is RemoteConfigListEvent.OnToggleRemoteConfig -> toggleRemoteConfigItem(event.id)
                is RemoteConfigListEvent.OnBackClick -> navigateBack()
            }
        }
    }

    private suspend fun loadData() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        // Simulate loading data
        kotlinx.coroutines.delay(2000)
        val items = listOf(
            RemoteConfigItem(id = "1", name = "Feature A", enabled = true),
            RemoteConfigItem(id = "2", name = "Feature B", enabled = false),
            RemoteConfigItem(id = "3", name = "Feature C", enabled = true),
        )
        _uiState.value = _uiState.value.copy(isLoading = false, remoteConfigItems = items)
    }

    private fun toggleRemoteConfigItem(id: String) {
        val updatedItems = _uiState.value.remoteConfigItems.map { item ->
            if (item.id == id) {
                item.copy(enabled = !item.enabled)
            } else {
                item
            }
        }
        _uiState.value = _uiState.value.copy(remoteConfigItems = updatedItems)
    }

    private suspend fun navigateBack() {
        _sideEffect.send(RemoteConfigListSideEffect.NavigateBack)
    }
}