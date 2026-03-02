package mx.dev.cmg.android.code.ui.feature.datapersistence.viewmodel

sealed interface NoteDetailSideEffect {
    data object NavigateBack : NoteDetailSideEffect
}