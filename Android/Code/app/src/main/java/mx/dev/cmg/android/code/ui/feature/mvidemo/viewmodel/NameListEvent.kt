package mx.dev.cmg.android.code.ui.feature.mvidemo.viewmodel

sealed interface NameListEvent {
    data object NavigateBack : NameListEvent
    data class TypedNameChange(val newName: String) : NameListEvent
    data object AddName : NameListEvent
}