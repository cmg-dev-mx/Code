package mx.dev.cmg.android.code.data.mapper

import mx.dev.cmg.android.code.core.model.Nota
import mx.dev.cmg.android.code.datasource.local.database.model.NotaEntity

object NotaMapper {

    fun NotaEntity.toNota(): Nota {
        return Nota(
            id = this.id,
            title = this.title,
            content = this.content,
            timestamp = this.timestamp
        )
    }

    fun Nota.toNotaEntity(): NotaEntity {
        return NotaEntity(
            id = this.id,
            title = this.title,
            content = this.content,
            timestamp = this.timestamp
        )
    }
}