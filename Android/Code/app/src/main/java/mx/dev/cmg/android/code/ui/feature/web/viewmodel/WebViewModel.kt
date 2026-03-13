package mx.dev.cmg.android.code.ui.feature.web.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import mx.dev.cmg.android.code.ui.util.launchEvent
import mx.dev.cmg.android.code.ui.util.sendEffect
import mx.dev.cmg.android.code.ui.util.update

class WebViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(WebUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<WebSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onEvent(event: WebEvent) = launchEvent {
        when (event) {
            is WebEvent.NavigateBack -> _sideEffect.sendEffect(WebSideEffect.NavigateBack)
            is WebEvent.SetInitialUrl -> setInitialUrl(event)
        }
    }

    private fun setInitialUrl(event: WebEvent.SetInitialUrl) {
        _uiState.update { copy(url = event.url) }
    }
}