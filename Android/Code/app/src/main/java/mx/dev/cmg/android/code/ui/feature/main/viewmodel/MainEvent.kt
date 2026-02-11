package mx.dev.cmg.android.code.ui.feature.main.viewmodel

sealed interface MainEvent {
    object OnLoad : MainEvent
    object NavigateToRemoteConfigList: MainEvent
}