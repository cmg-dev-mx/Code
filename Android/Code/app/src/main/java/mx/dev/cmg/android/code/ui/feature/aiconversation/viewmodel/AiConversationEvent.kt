package mx.dev.cmg.android.code.ui.feature.aiconversation.viewmodel

sealed interface AiConversationEvent {
    data object NavigateBack : AiConversationEvent

}
