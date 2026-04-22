package mx.dev.cmg.android.code.ui.feature.textspeech.viewmodel

sealed interface TextSpeechSideEffect {
    data object NavigateBack : TextSpeechSideEffect
}