package mx.dev.cmg.android.code.ui.feature.rest.viewmodel

sealed interface RestSideEffect {
    data object NavigateBack : RestSideEffect
    data class ShowToast(val message: String) : RestSideEffect
}