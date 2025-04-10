package com.example.weighttrackerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weighttracker.ui.WeightTrackerApp
import com.example.weighttracker.viewmodel.WeightViewModel
import com.example.weighttracker.viewmodel.WeightViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Use custom ViewModel factory here
            val viewModel: WeightViewModel = viewModel(
                factory = WeightViewModelFactory(application)
            )
            WeightTrackerApp(viewModel)
        }
    }
}
