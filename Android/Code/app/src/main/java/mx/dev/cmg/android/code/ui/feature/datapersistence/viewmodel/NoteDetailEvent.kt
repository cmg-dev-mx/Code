package mx.dev.cmg.android.code.ui.feature.datapersistence.viewmodel

sealed interface NoteDetailEvent {
    data object NavigateBack : NoteDetailEvent
}
