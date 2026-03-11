package mx.dev.cmg.android.code.ui.feature.web.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import mx.dev.cmg.android.code.ui.util.launchEvent
import mx.dev.cmg.android.code.ui.util.sendEffect
import mx.dev.cmg.android.code.ui.util.update

class WebViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(WebUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<WebSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    init {
        viewModelScope.launch {
            _uiState.update {
                copy(
                    url = "https://www.wikipedia.com"
                )
            }
        }
    }

    fun onEvent(event: WebEvent) = launchEvent {
        when (event) {
            is WebEvent.NavigateBack -> _sideEffect.sendEffect(WebSideEffect.NavigateBack)
        }
    }
}