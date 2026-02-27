package mx.dev.cmg.android.code.repository.notes

import mx.dev.cmg.android.code.core.model.Nota

class NoteRepositoryImpl : NoteRepository {

    override suspend fun getNotes(): List<Nota> {
        TODO("Not yet implemented")
    }

    override suspend fun getNoteById(id: Int): Nota? {
        TODO("Not yet implemented")
    }

    override suspend fun saveNote(note: Nota) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNoteById(id: Int) {
        TODO("Not yet implemented")
    }
}