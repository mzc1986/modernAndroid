package com.bluewave.modernapp.data.local.database

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity
data class OneModel(
    val name: String
){
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}

@Dao
interface OneModelDao {
    @Query("SELECT * FROM oneModel ORDER BY uid DESC LIMIT 10")
    fun getMyItems(): Flow<List<OneModel>>

    @Insert
    suspend fun insert(item: OneModel)
}