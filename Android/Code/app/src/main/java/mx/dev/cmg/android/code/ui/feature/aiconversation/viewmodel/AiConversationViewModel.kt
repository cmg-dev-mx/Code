package mx.dev.cmg.android.code.ui.feature.aiconversation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.ai.type.PublicPreviewAPI
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import mx.dev.cmg.android.code.data.repository.ai.AiRepository
import mx.dev.cmg.android.code.ui.util.launchEvent
import mx.dev.cmg.android.code.ui.util.sendEffect

@OptIn(PublicPreviewAPI::class)
class AiConversationViewModel(
    private val aiRepository: AiRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AiConversationUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<AiConversationSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    init {
        viewModelScope.launch {
            aiRepository.initialize()
                .onSuccess {
                    _sideEffect.sendEffect(AiConversationSideEffect.AiInitializationSuccess)
                }
                .onFailure {
                    _sideEffect.sendEffect(AiConversationSideEffect.AiInitializationFailed)
                }
        }
    }

    fun onEvent(event: AiConversationEvent) = launchEvent {
        when (event) {
            is AiConversationEvent.NavigateBack -> _sideEffect.sendEffect(AiConversationSideEffect.NavigateBack)
            is AiConversationEvent.StartListening -> toggleListening(true)
            is AiConversationEvent.StopListening -> toggleListening(false)
        }
    }

    private suspend fun toggleListening(isListening: Boolean) {
        _uiState.value = uiState.value.copy(isListening = isListening)
        if (isListening) {
            aiRepository.startAudioConversation()
        } else {
            aiRepository.stopAudioConversation()
        }
    }
}