package mx.dev.cmg.android.code.ui.feature.crash.viewmodel

sealed interface CrashSideEffect {
    data object NavigateBack : CrashSideEffect
}