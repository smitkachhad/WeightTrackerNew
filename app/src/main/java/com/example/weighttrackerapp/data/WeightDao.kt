package com.example.weighttracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WeightDao {

    @Query("SELECT * FROM weight_table ORDER BY id DESC")
    fun getAllWeights(): Flow<List<Weight>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weight: Weight)
}
