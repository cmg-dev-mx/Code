package mx.dev.cmg.android.code.ui.feature.aiconversation.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import com.google.firebase.ai.type.LiveSession
import com.google.firebase.ai.type.PublicPreviewAPI
import com.google.firebase.ai.type.ResponseModality
import com.google.firebase.ai.type.liveGenerationConfig
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import mx.dev.cmg.android.code.ui.util.launchEvent
import mx.dev.cmg.android.code.ui.util.sendEffect

@OptIn(PublicPreviewAPI::class)
class AiConversationViewModel : ViewModel() {

    private val liveModel = Firebase.ai(backend = GenerativeBackend.googleAI())
        .liveModel(
            modelName = "gemini-2.5-flash-native-audio-preview-12-2025",
            generationConfig = liveGenerationConfig {
                responseModality = ResponseModality.AUDIO
            }
        )

    private lateinit var session: LiveSession

    init {
        viewModelScope.launch {
            session = liveModel.connect()
        }
    }

    private val _uiState = MutableStateFlow(AiConversationUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<AiConversationSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onEvent(event: AiConversationEvent) = launchEvent {
        when (event) {
            is AiConversationEvent.NavigateBack -> _sideEffect.sendEffect(AiConversationSideEffect.NavigateBack)
            is AiConversationEvent.StartListening -> toggleListening(true)
            is AiConversationEvent.StopListening -> toggleListening(false)
        }
    }

    @SuppressLint("MissingPermission")
    private suspend fun toggleListening(isListening: Boolean) {
        _uiState.value = uiState.value.copy(isListening = isListening)
            if (isListening) {
                session.startAudioConversation()
            } else {
                session.stopAudioConversation()
            }
    }
}