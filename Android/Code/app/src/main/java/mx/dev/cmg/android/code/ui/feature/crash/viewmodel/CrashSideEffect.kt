package mx.dev.cmg.android.code.ui.feature.crash.viewmodel

sealed interface CrashSideEffect {
    object NavigateBack : CrashSideEffect
}