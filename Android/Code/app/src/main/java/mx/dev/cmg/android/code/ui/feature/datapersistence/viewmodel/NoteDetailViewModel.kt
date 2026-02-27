package mx.dev.cmg.android.code.ui.feature.datapersistence.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import mx.dev.cmg.android.code.core.model.Nota
import mx.dev.cmg.android.code.repository.notes.NoteRepository
import mx.dev.cmg.android.code.ui.base.viewmodel.launchEvent

class NoteDetailViewModel(
    private val repository: NoteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NoteDetailUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<NoteDetailSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onEvent(event: NoteDetailEvent) = launchEvent {
        when (event) {
            is NoteDetailEvent.NavigateBack -> _sideEffect.send(NoteDetailSideEffect.NavigateBack)
            is NoteDetailEvent.UpdateTitle -> updatetitle(event.title)
            is NoteDetailEvent.UpdateContent -> updateContent(event.content)
            is NoteDetailEvent.SaveNote -> saveNote()
        }
    }

    private suspend fun updatetitle(title: String) {
        _uiState.emit(uiState.value.copy(title = title))
    }

    private suspend fun updateContent(content: String) {
        _uiState.emit(uiState.value.copy(content = content))
    }

    private suspend fun saveNote() {
        val nota = uiState.value.let {
            Nota(
                id = it.noteId,
                title = it.title,
                content = it.content
            )
        }
        repository.saveNote(nota)

        _sideEffect.send(NoteDetailSideEffect.NavigateBack)
    }
}
