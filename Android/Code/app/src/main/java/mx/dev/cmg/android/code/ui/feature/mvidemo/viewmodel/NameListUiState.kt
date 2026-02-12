package mx.dev.cmg.android.code.ui.feature.mvidemo.viewmodel

data class NameListUiState(
    val names: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val typedName: String = ""
)