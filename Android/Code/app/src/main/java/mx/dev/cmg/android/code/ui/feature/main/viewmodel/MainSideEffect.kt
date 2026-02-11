package mx.dev.cmg.android.code.ui.feature.main.viewmodel

sealed interface MainSideEffect {
    object NavigateToNameList : MainSideEffect
}