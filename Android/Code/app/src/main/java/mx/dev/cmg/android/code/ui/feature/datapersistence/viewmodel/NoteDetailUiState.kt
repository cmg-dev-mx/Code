package mx.dev.cmg.android.code.ui.feature.datapersistence.viewmodel

data class NoteDetailUiState(
    val noteId: Int = 0,
    val title: String = "",
    val content: String = "",
    val error: Boolean = false
) {
    fun readyToSave(): Boolean {
        return title.isNotBlank() && content.isNotBlank() && !error
    }
}
