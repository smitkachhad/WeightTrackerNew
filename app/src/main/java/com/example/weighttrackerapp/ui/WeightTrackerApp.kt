package com.example.weighttracker.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.weighttrackerapp.R
import com.example.weighttracker.viewmodel.WeightViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun WeightTrackerApp(viewModel: WeightViewModel) { // <-- Parameter added
    val context = LocalContext.current
    val weightList by viewModel.weightList.collectAsState(initial = emptyList())
    var input by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Weight Tracker", style = MaterialTheme.typography.headlineLarge, color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                label = { Text("Enter weight (kg)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                val weightValue = input.toFloatOrNull()
                if (weightValue != null) {
                    scope.launch {
                        viewModel.addWeight(weightValue)
                        input = ""
                    }
                } else {
                    Toast.makeText(context, "Enter valid number", Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Add Weight")
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(weightList) { weight ->
                    Text(
                        text = "${weight.weight} kg - ${SimpleDateFormat("dd MMM yyyy").format(Date(weight.timestamp))}",
                        color = Color.White
                    )
                    Divider(color = Color.LightGray)
                }
            }
        }
    }
}
