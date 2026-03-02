package mx.dev.cmg.android.code.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import mx.dev.cmg.android.code.datasource.local.database.dao.NotaDao
import mx.dev.cmg.android.code.datasource.local.database.model.NotaEntity

@Database(
    entities = [
        NotaEntity::class
    ],
    version = 1,
)
abstract class NoteDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "note_database"
    }

    abstract fun notaDao(): NotaDao
}