package com.example.taskmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.taskmate.data.SettingsDataStore
import com.example.taskmate.navigation.NavGraph
import com.example.taskmate.ui.theme.TaskMateTheme
import com.example.taskmate.viewmodel.TaskViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            val settingsStore = remember { SettingsDataStore(context) }
            val darkModeEnabled by settingsStore.darkModeFlow.collectAsState(initial = false)
            val taskViewModel: TaskViewModel = viewModel()

            TaskMateTheme(darkTheme = darkModeEnabled) {
                NavGraph(taskViewModel = taskViewModel, settingsStore = settingsStore)
            }
        }
    }
}