package mx.dev.cmg.android.code.ui.feature.crash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CrashViewModel : ViewModel() {

    private val _sideEffect = Channel<CrashSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onEvent(event: CrashEvent) {
        viewModelScope.launch {
            when (event) {
                is CrashEvent.OnCrashButtonClicked -> {
                    throw RuntimeException("¡Esto es un crash de ejemplo!")
                }

                is CrashEvent.NavigateBack -> {
                    navigateBack()
                }
            }
        }
    }

    private suspend fun navigateBack() {
        _sideEffect.send(CrashSideEffect.NavigateBack)
    }
}