package mx.dev.cmg.android.code.repository.notes

import kotlinx.coroutines.flow.Flow
import mx.dev.cmg.android.code.core.model.Nota

interface NoteRepository {
    suspend fun getNotes(): Flow<List<Nota>>
    suspend fun getNoteById(id: Int): Nota?
    suspend fun saveNote(note: Nota)
    suspend fun updateNote(note: Nota)
    suspend fun deleteNoteById(id: Int)
}