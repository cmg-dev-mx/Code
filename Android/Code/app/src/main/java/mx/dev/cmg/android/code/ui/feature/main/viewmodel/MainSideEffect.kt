package mx.dev.cmg.android.code.ui.feature.main.viewmodel

sealed interface MainSideEffect {
    data object NavigateToNameList : MainSideEffect
    data object NavigateToCrashlytics : MainSideEffect
    data object NavigateToPersistence: MainSideEffect
}