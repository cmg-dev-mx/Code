package mx.dev.cmg.android.code.ui.feature.crash.viewmodel

sealed interface CrashEvent {
    object OnCrashButtonClicked : CrashEvent
    object NavigateBack : CrashEvent
}