package com.example.feel.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface FeelingDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFeeling(feeling: Feeling)

    @Query("SELECT * FROM feeling_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Feeling>>
}