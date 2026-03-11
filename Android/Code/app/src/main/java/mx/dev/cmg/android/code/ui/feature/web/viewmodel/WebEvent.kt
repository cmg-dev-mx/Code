package mx.dev.cmg.android.code.ui.feature.web.viewmodel

sealed interface WebEvent {
    data object NavigateBack : WebEvent
}