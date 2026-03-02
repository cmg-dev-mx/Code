package mx.dev.cmg.android.code.datasource.local

import kotlinx.coroutines.flow.Flow
import mx.dev.cmg.android.code.datasource.local.database.dao.NotaDao
import mx.dev.cmg.android.code.datasource.local.database.model.NotaEntity

class LocalDataSourceImpl(
    private val notaDao: NotaDao
) : LocalDataSource {

    override suspend fun getNotes(): Flow<List<NotaEntity>> {
        return notaDao.getAllNotes()
    }

    override suspend fun getNoteById(id: Int): NotaEntity? {
        return notaDao.getNoteById(id)
    }

    override suspend fun saveNote(note: NotaEntity) {
        notaDao.insertNote(note)
    }

    override suspend fun updateNote(note: NotaEntity) {
        notaDao.updateNote(note)
    }

    override suspend fun deleteNoteById(id: Int) {
        val note = notaDao.getNoteById(id)
        if (note != null) {
            notaDao.deleteNote(note)
        }
    }
}