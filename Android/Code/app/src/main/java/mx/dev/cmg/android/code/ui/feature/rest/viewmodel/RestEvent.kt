package mx.dev.cmg.android.code.ui.feature.rest.viewmodel

sealed interface RestEvent {
    data object NavigateBack : RestEvent
    data object GetQuote : RestEvent
}