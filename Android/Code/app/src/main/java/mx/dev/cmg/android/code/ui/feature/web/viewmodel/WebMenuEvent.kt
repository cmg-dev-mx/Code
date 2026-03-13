package mx.dev.cmg.android.code.ui.feature.web.viewmodel

sealed interface WebMenuEvent {
    data object NavigateBack : WebMenuEvent
    data object OpenWebInLayout : WebMenuEvent
    data object OpenCustomTab : WebMenuEvent
}
