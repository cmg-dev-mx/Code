package mx.dev.cmg.android.code.ui.feature.textspeech.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import mx.dev.cmg.android.code.data.repository.textspeech.TextSpeechRepository
import mx.dev.cmg.android.code.ui.util.launchEvent
import mx.dev.cmg.android.code.ui.util.sendEffect

class TextSpeechViewModel(
    private val textSpeechRepository: TextSpeechRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TextSpeechUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<TextSpeechSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onEvent(event: TextSpeechEvent) = launchEvent {
        when (event) {
            is TextSpeechEvent.NavigateBack -> {
                _sideEffect.sendEffect(TextSpeechSideEffect.NavigateBack)
            }
            is TextSpeechEvent.OnTextChange -> {
                _uiState.value = _uiState.value.copy(text = event.text)
            }
            is TextSpeechEvent.OnTextToSpeechClick -> {
                startSpeaking(_uiState.value.text)
            }
        }
    }

    private fun startSpeaking(text: String) {
        textSpeechRepository.speak(text)
        _uiState.value = _uiState.value.copy(text = "")
    }
}