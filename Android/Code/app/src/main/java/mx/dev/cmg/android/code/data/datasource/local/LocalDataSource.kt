package mx.dev.cmg.android.code.data.datasource.local

import kotlinx.coroutines.flow.Flow
import mx.dev.cmg.android.code.data.datasource.local.database.model.NoteEntity

interface LocalDataSource {
    suspend fun getNotes(): Flow<List<NoteEntity>>
    suspend fun getNoteById(id: Int): NoteEntity?
    suspend fun saveNote(note: NoteEntity)
    suspend fun updateNote(note: NoteEntity)
    suspend fun deleteNoteById(id: Int)
}