package mx.dev.cmg.android.code.ui.feature.aiconversation.viewmodel

sealed interface AiConversationSideEffect {
    data object NavigateBack : AiConversationSideEffect
    data object AiInitializationSuccess : AiConversationSideEffect
    data object AiInitializationFailed : AiConversationSideEffect
}
