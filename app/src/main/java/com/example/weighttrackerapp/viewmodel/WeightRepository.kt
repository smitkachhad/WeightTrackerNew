package com.example.weighttracker.repository

import com.example.weighttracker.data.Weight
import com.example.weighttracker.data.WeightDao
import kotlinx.coroutines.flow.Flow

class WeightRepository(private val dao: WeightDao) {
    val allWeights: Flow<List<Weight>> = dao.getAllWeights()

    suspend fun insert(weight: Weight) {
        dao.insert(weight)
    }
}
