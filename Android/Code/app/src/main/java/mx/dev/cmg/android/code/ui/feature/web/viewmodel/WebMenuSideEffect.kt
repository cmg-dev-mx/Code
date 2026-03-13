package mx.dev.cmg.android.code.ui.feature.web.viewmodel

sealed interface WebMenuSideEffect {
    data object NavigateBack : WebMenuSideEffect
    data class OpenWebInLayout(val url: String) : WebMenuSideEffect
    data class OpenCustomTab(val url: String) : WebMenuSideEffect
}