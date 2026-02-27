package mx.dev.cmg.android.code.repository.notes

import mx.dev.cmg.android.code.core.model.Nota

interface NoteRepository {
    suspend fun getNotes(): List<Nota>
    suspend fun getNoteById(id: Int): Nota?
    suspend fun saveNote(note: Nota)
    suspend fun deleteNoteById(id: Int)
}