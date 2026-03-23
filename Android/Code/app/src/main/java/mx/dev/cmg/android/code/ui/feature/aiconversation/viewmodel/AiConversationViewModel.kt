package mx.dev.cmg.android.code.ui.feature.aiconversation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import mx.dev.cmg.android.code.ui.util.launchEvent
import mx.dev.cmg.android.code.ui.util.sendEffect

class AiConversationViewModel : ViewModel() {

    private val _sideEffect = Channel<AiConversationSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onEvent(event: AiConversationEvent) = launchEvent {
        when (event) {
            is AiConversationEvent.NavigateBack -> _sideEffect.sendEffect(AiConversationSideEffect.NavigateBack)
        }
    }
}