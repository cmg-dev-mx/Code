package mx.dev.cmg.android.code.ui.feature.main.viewmodel

sealed interface MainEvent {
    data object NavigateToNameList : MainEvent
    data object NavigateToCrashlytics : MainEvent
    data object NavigateToPersistence : MainEvent
    data object NavigateToSharedPreferences : MainEvent
    data object NavigateToRestApi : MainEvent
    data object NavigateToWebView : MainEvent
    data object NavigateToAiConversation : MainEvent
}