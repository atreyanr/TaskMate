package com.example.taskmate.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmate.data.SettingsDataStore
import com.example.taskmate.viewmodel.TaskViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue

@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: TaskViewModel,
    settingsStore: SettingsDataStore
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val darkMode by settingsStore.darkModeFlow.collectAsState(initial = false)

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Settings", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.clearTasks() },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Text("Clear All Tasks")
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Dark Mode")
            Switch(
                checked = darkMode,
                onCheckedChange = {
                    scope.launch { settingsStore.setDarkMode(it) }
                }
            )
        }
    }
}
