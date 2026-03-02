package mx.dev.cmg.android.code.ui.feature.crash.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import mx.dev.cmg.android.code.ui.util.launchEvent
import mx.dev.cmg.android.code.ui.util.sendEffect

class CrashViewModel : ViewModel() {

    private val _sideEffect = Channel<CrashSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onEvent(event: CrashEvent) = launchEvent {
        when (event) {
            is CrashEvent.OnCrashButtonClicked -> throwExampleError()
            is CrashEvent.NavigateBack -> _sideEffect.sendEffect(CrashSideEffect.NavigateBack)
        }
    }

    private fun throwExampleError(): Nothing {
        throw RuntimeException("¡Esto es un crash de ejemplo!")
    }
}