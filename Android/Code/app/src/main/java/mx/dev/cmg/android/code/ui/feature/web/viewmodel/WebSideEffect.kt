package mx.dev.cmg.android.code.ui.feature.web.viewmodel

sealed interface WebSideEffect {
    data object NavigateBack : WebSideEffect
}