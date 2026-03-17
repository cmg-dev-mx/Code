package mx.dev.cmg.android.code.ui.feature.web.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import mx.dev.cmg.android.code.ui.util.launchEvent
import mx.dev.cmg.android.code.ui.util.sendEffect

class WebMenuViewModel : ViewModel() {

    private val url = "https://www.wikipedia.com"

    private val _sideEffect = Channel<WebMenuSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onEvent(event: WebMenuEvent) = launchEvent {
        when (event) {
            is WebMenuEvent.NavigateBack -> _sideEffect.sendEffect(WebMenuSideEffect.NavigateBack)
            is WebMenuEvent.OpenWebInLayout -> _sideEffect.sendEffect(
                WebMenuSideEffect.OpenWebInLayout(url)
            )
            is WebMenuEvent.OpenCustomTab -> _sideEffect.sendEffect(
                WebMenuSideEffect.OpenCustomTab(url)
            )
        }
    }
}