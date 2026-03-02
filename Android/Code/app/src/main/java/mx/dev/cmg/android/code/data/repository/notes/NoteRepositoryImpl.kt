package mx.dev.cmg.android.code.data.repository.notes

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import mx.dev.cmg.android.code.data.datasource.local.LocalDataSource
import mx.dev.cmg.android.code.data.mapper.NoteMapper.toNote
import mx.dev.cmg.android.code.data.mapper.NoteMapper.toNoteEntity
import mx.dev.cmg.android.code.domain.Note

class NoteRepositoryImpl(
    private val localDataSource: LocalDataSource
) : NoteRepository {

    override suspend fun getNotes(): Flow<List<Note>> {
        return localDataSource.getNotes().map { noteEntity ->
            noteEntity.map { it.toNote() }
        }
    }

    override suspend fun getNoteById(id: Int): Note? {
        val noteEntity = localDataSource.getNoteById(id)
        return noteEntity?.toNote()
    }

    override suspend fun saveNote(note: Note) {
        val noteEntity = note.toNoteEntity()
        localDataSource.saveNote(noteEntity)
    }

    override suspend fun updateNote(note: Note) {
        val noteEntity = note.toNoteEntity()
        localDataSource.updateNote(noteEntity)
    }

    override suspend fun deleteNoteById(id: Int) {
        localDataSource.deleteNoteById(id)
    }
}