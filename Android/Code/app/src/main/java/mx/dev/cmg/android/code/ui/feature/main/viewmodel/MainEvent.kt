package mx.dev.cmg.android.code.ui.feature.main.viewmodel

sealed interface MainEvent {
    data object OnLoad : MainEvent
    data object NavigateToRemoteConfigList: MainEvent
}