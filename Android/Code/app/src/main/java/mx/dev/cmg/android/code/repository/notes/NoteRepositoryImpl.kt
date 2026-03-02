package mx.dev.cmg.android.code.repository.notes

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import mx.dev.cmg.android.code.core.model.Nota
import mx.dev.cmg.android.code.data.mapper.NotaMapper.toNota
import mx.dev.cmg.android.code.data.mapper.NotaMapper.toNotaEntity
import mx.dev.cmg.android.code.datasource.local.LocalDataSource

class NoteRepositoryImpl(
    private val localDataSource: LocalDataSource
) : NoteRepository {

    override suspend fun getNotes(): Flow<List<Nota>> {
        return localDataSource.getNotes().map { notaEntity ->
            notaEntity.map { it.toNota() }
        }
    }

    override suspend fun getNoteById(id: Int): Nota? {
        val notaEntity = localDataSource.getNoteById(id)
        return notaEntity?.toNota()
    }

    override suspend fun saveNote(note: Nota) {
        val notaEntity = note.toNotaEntity()
        localDataSource.saveNote(notaEntity)
    }

    override suspend fun updateNote(note: Nota) {
        val notaEntity = note.toNotaEntity()
        localDataSource.updateNote(notaEntity)
    }

    override suspend fun deleteNoteById(id: Int) {
        localDataSource.deleteNoteById(id)
    }
}