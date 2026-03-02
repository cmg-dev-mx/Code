package mx.dev.cmg.android.code.data.datasource.local

import kotlinx.coroutines.flow.Flow
import mx.dev.cmg.android.code.data.datasource.local.database.dao.NoteDao
import mx.dev.cmg.android.code.data.datasource.local.database.model.NoteEntity

class LocalDataSourceImpl(
    private val noteDao: NoteDao
) : LocalDataSource {

    override suspend fun getNotes(): Flow<List<NoteEntity>> {
        return noteDao.getAllNotes()
    }

    override suspend fun getNoteById(id: Int): NoteEntity? {
        return noteDao.getNoteById(id)
    }

    override suspend fun saveNote(note: NoteEntity) {
        noteDao.insertNote(note)
    }

    override suspend fun updateNote(note: NoteEntity) {
        noteDao.updateNote(note)
    }

    override suspend fun deleteNoteById(id: Int) {
        val note = noteDao.getNoteById(id)
        if (note != null) {
            noteDao.deleteNote(note)
        }
    }
}