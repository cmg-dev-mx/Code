package mx.dev.cmg.android.code.ui.feature.aiconversation.viewmodel

sealed interface AiConversationSideEffect {
        data object NavigateBack : AiConversationSideEffect
}
