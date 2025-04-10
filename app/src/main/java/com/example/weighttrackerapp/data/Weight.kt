package com.example.weighttracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weight_table")
data class Weight(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val weight: Float,
    val timestamp: Long = System.currentTimeMillis()
)
