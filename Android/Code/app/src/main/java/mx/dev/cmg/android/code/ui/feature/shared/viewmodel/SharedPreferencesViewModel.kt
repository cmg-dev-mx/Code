package mx.dev.cmg.android.code.ui.feature.shared.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import mx.dev.cmg.android.code.data.repository.shared.SharedPreferencesRepository
import mx.dev.cmg.android.code.ui.util.launchEvent
import mx.dev.cmg.android.code.ui.util.sendEffect

class SharedPreferencesViewModel(
    private val repository: SharedPreferencesRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(SharedUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<SharedSideEffect>(capacity = Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    init {
        loadInitialData()
    }

    fun onEvent(event: SharedPreferencesEvent) = launchEvent {
        when (event) {
            is SharedPreferencesEvent.NavigateBack -> _sideEffect.sendEffect(SharedSideEffect.NavigateBack)
            is SharedPreferencesEvent.ToggleEdit -> updateToggle(event.enabled)
            is SharedPreferencesEvent.OnValueChange -> updateText(event.value)
            is SharedPreferencesEvent.SaveValue -> saveValue()

        }
    }

    private fun loadInitialData() = launchEvent {
        val value = repository.getValue()
        val editEnabled = repository.isEditEnabled()

        _uiState.value = SharedUiState(
            modifiedValue = value,
            editEnabled = editEnabled
        )
    }

    private suspend fun updateToggle(enabled: Boolean) {
        repository.updateEditEnabled(enabled)
        _uiState.value = _uiState.value.copy(editEnabled = enabled)
    }

    private fun updateText(newString: String) {
        _uiState.value = _uiState.value.copy(modifiedValue = newString)
    }

    private suspend fun saveValue() {
        repository.updateValue(_uiState.value.modifiedValue)
        _sideEffect.sendEffect(SharedSideEffect.SaveSuccess)
    }
}