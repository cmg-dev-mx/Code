package mx.dev.cmg.android.code.ui.feature.datapersistence.viewmodel

sealed interface NoteDetailEvent {
    data object NavigateBack : NoteDetailEvent
    data class UpdateTitle(val title: String) : NoteDetailEvent
    data class UpdateContent(val content: String) : NoteDetailEvent
    data object SaveNote : NoteDetailEvent
}
