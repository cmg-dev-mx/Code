package mx.dev.cmg.android.code.data.repository.notes

import kotlinx.coroutines.flow.Flow
import mx.dev.cmg.android.code.domain.Note

interface NoteRepository {
    suspend fun getNotes(): Flow<List<Note>>
    suspend fun getNoteById(id: Int): Note?
    suspend fun saveNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun deleteNoteById(id: Int)
}