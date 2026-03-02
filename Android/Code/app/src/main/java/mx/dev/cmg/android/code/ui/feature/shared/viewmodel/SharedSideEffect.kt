package mx.dev.cmg.android.code.ui.feature.shared.viewmodel

sealed interface SharedSideEffect {
    data object NavigateBack : SharedSideEffect
    data object SaveSuccess: SharedSideEffect
}