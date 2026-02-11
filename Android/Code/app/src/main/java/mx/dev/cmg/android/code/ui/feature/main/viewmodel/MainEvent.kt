package mx.dev.cmg.android.code.ui.feature.main.viewmodel

sealed interface MainEvent {
    data object NavigateToNameList : MainEvent
}