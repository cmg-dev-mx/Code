package mx.dev.cmg.android.code.ui.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class MviViewModel<STATE, EVENT, SIDE_EFFECT>(
    initialState: STATE
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<STATE> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SIDE_EFFECT>(
        replay = 0,
        extraBufferCapacity = 64
    )
    val sideEffect: SharedFlow<SIDE_EFFECT> = _sideEffect.asSharedFlow()

    fun onEvent(event: EVENT) {
        viewModelScope.launch {
            handleEvent(event)
        }
    }

    protected abstract suspend fun handleEvent(event: EVENT)

    protected fun updateState(reducer: STATE.() -> STATE) {
        _uiState.value = _uiState.value.reducer()
    }

    protected val currentState: STATE
        get() = _uiState.value

    protected suspend fun emitSideEffect(effect: SIDE_EFFECT) {
        _sideEffect.emit(effect)
    }
}
