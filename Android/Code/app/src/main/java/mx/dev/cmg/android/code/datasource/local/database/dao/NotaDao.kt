package mx.dev.cmg.android.code.datasource.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import mx.dev.cmg.android.code.datasource.local.database.model.NotaEntity

@Dao
interface NotaDao {

    @Query("SELECT * FROM NotaEntity")
    fun getAllNotes(): Flow<List<NotaEntity>>

    @Query("SELECT * FROM NotaEntity WHERE id = :id")
    suspend fun getNoteById(id: Int): NotaEntity?

    @Insert
    suspend fun insertNote(note: NotaEntity)

    @Delete
    suspend fun deleteNote(note: NotaEntity)

    @Update
    suspend fun updateNote(note: NotaEntity)
}