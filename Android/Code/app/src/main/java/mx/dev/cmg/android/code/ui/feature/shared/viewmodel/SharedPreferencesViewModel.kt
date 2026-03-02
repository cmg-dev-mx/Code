package mx.dev.cmg.android.code.ui.feature.shared.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import mx.dev.cmg.android.code.ui.util.launchEvent
import mx.dev.cmg.android.code.ui.util.sendEffect

class SharedPreferencesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SharedUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<SharedSideEffect>(capacity = Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onEvent(event: SharedPreferencesEvent) = launchEvent {
        when (event) {
            is SharedPreferencesEvent.NavigateBack -> _sideEffect.sendEffect(SharedSideEffect.NavigateBack)
            is SharedPreferencesEvent.ToggleEdit -> updateToggle(event.enabled)
            is SharedPreferencesEvent.OnValueChange -> updateText(event.value)
            is SharedPreferencesEvent.SaveValue -> saveValue()

        }
    }

    private fun updateToggle(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(editEnabled = enabled)
    }

    private fun updateText(newString: String) {
        _uiState.value = _uiState.value.copy(modifiedValue = newString)
    }

    private suspend fun saveValue() {
        // TODO Not yet implemented
        _sideEffect.sendEffect(SharedSideEffect.SaveSuccess)
    }
}