package mx.dev.cmg.android.code.datasource.local.database

import mx.dev.cmg.android.code.datasource.local.database.model.NotaEntity

interface LocalDataSource {
    suspend fun getNotes(): List<NotaEntity>
    suspend fun getNoteById(id: Int): NotaEntity?
    suspend fun saveNote(note: NotaEntity)
    suspend fun deleteNoteById(id: Int)
}