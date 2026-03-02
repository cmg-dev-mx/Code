package mx.dev.cmg.android.code.data.datasource.local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val content: String,
    val timestamp: Long
)
