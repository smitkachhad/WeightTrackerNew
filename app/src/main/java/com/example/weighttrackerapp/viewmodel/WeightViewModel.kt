package com.example.weighttracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.weighttracker.data.Weight
import com.example.weighttracker.data.WeightDatabase
import com.example.weighttracker.repository.WeightRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WeightViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = WeightDatabase.getDatabase(application).weightDao()
    private val repository = WeightRepository(dao)

    val weightList = repository.allWeights.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun addWeight(weight: Float) {
        viewModelScope.launch {
            repository.insert(Weight(weight = weight))
        }
    }
}
