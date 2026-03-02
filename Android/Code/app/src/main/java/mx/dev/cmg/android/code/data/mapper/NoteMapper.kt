package mx.dev.cmg.android.code.data.mapper

import mx.dev.cmg.android.code.data.datasource.local.database.model.NoteEntity
import mx.dev.cmg.android.code.domain.Note

object NoteMapper {

    fun NoteEntity.toNote(): Note {
        return Note(
            id = this.id,
            title = this.title,
            content = this.content,
            timestamp = this.timestamp
        )
    }

    fun Note.toNoteEntity(): NoteEntity {
        return NoteEntity(
            id = this.id,
            title = this.title,
            content = this.content,
            timestamp = this.timestamp
        )
    }
}
