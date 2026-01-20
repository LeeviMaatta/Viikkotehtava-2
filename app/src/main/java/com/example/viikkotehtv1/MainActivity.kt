package com.example.viikkotehtv1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikkotehtv1.ViewModel.TaskViewModel
import com.example.viikkotehtv1.ui.HomeScreen
import com.example.viikkotehtv1.ui.theme.Viikkotehtävä1Theme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Viikkotehtävä1Theme {
                val viewModel: TaskViewModel = viewModel()
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    HomeScreen(viewModel)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(TaskViewModel())
}