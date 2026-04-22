package mx.dev.cmg.android.code.ui.feature.textspeech.viewmodel

sealed interface TextSpeechEvent {
    data object NavigateBack : TextSpeechEvent
    data class OnTextChange(val text: String) : TextSpeechEvent
    data object OnTextToSpeechClick : TextSpeechEvent
}