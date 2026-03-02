package mx.dev.cmg.android.code.ui.feature.datapersistence.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import mx.dev.cmg.android.code.repository.notes.NoteRepository
import mx.dev.cmg.android.code.ui.base.viewmodel.launchEvent
import mx.dev.cmg.android.code.ui.base.viewmodel.sendEffect

class DataPersistenceViewModel(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DataPersistenceUiState())
    val uiState: StateFlow<DataPersistenceUiState> = _uiState.asStateFlow()

    private val _sideEffect = Channel<DataPersistenceSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    init {
        loadNotes()
    }

    fun onEvent(event: DataPersistenceEvent) = launchEvent {
        when (event) {
            is DataPersistenceEvent.NavigateBack -> _sideEffect.sendEffect(DataPersistenceSideEffect.NavigateBack)
            is DataPersistenceEvent.CreateNote -> _sideEffect.sendEffect(DataPersistenceSideEffect.NoteDetail(id = 0))
            is DataPersistenceEvent.EditNote -> _sideEffect.sendEffect(DataPersistenceSideEffect.NoteDetail(id = event.id))
            is DataPersistenceEvent.DeleteNote -> deleteNote(event.id)
        }
    }

    private fun loadNotes() {
        viewModelScope.launch {
            noteRepository.getNotes().collect { notes ->
                _uiState.value = DataPersistenceUiState(notes = notes)
            }
        }
    }

    private suspend fun deleteNote(id: Int) {
        noteRepository.deleteNoteById(id)
    }
}
