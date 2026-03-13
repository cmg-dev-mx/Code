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

class WebMenuViewModel : ViewModel() {

    private val _url = MutableStateFlow("")
    val url = _url.asStateFlow()

    private val _sideEffect = Channel<WebMenuSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    init {
        viewModelScope.launch {
            _url.update { "https://www.wikipedia.com" }
        }
    }

    fun onEvent(event: WebMenuEvent) = launchEvent {
        when (event) {
            is WebMenuEvent.NavigateBack -> _sideEffect.sendEffect(WebMenuSideEffect.NavigateBack)
            is WebMenuEvent.OpenWebInLayout -> _sideEffect.sendEffect(
                WebMenuSideEffect.OpenWebInLayout(url.value)
            )
            is WebMenuEvent.OpenCustomTab -> _sideEffect.sendEffect(
                WebMenuSideEffect.OpenCustomTab(url.value)
            )
        }
    }
}