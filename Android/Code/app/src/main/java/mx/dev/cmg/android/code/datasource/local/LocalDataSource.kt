package mx.dev.cmg.android.code.datasource.local

import kotlinx.coroutines.flow.Flow
import mx.dev.cmg.android.code.datasource.local.database.model.NotaEntity

interface LocalDataSource {
    suspend fun getNotes(): Flow<List<NotaEntity>>
    suspend fun getNoteById(id: Int): NotaEntity?
    suspend fun saveNote(note: NotaEntity)
    suspend fun updateNote(note: NotaEntity)
    suspend fun deleteNoteById(id: Int)
}