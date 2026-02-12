package mx.dev.cmg.android.code.ui.feature.mvidemo.viewmodel

sealed interface NameListSideEffect {
    data object NavigateBack : NameListSideEffect
}