package mx.dev.cmg.android.code.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import mx.dev.cmg.android.code.data.datasource.local.database.dao.NoteDao
import mx.dev.cmg.android.code.data.datasource.local.database.model.NoteEntity

@Database(
    entities = [
        NoteEntity::class
    ],
    version = 1,
)
abstract class NoteDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "note_database"
    }

    abstract fun noteDao(): NoteDao
}