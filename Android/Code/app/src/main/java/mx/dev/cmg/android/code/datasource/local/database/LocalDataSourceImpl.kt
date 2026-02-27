package mx.dev.cmg.android.code.datasource.local.database

import mx.dev.cmg.android.code.datasource.local.database.model.NotaEntity

class LocalDataSourceImpl : LocalDataSource {

    override suspend fun getNotes(): List<NotaEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getNoteById(id: Int): NotaEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun saveNote(note: NotaEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNoteById(id: Int) {
        TODO("Not yet implemented")
    }
}