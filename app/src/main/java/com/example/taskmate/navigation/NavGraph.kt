package com.example.taskmate.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskmate.ui.screens.home.HomeScreen
import com.example.taskmate.ui.screens.CompletedScreen
import com.example.taskmate.ui.screens.SettingsScreen
import com.example.taskmate.ui.components.BottomNavBar
import androidx.compose.material3.Scaffold
import com.example.taskmate.data.SettingsDataStore
import com.example.taskmate.viewmodel.TaskViewModel

@Composable
fun NavGraph(
    taskViewModel: TaskViewModel,
    settingsStore: SettingsDataStore,
    startDestination: String = "home"
) {
    val navController: NavHostController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen(taskViewModel) }
            composable("completed") { CompletedScreen(taskViewModel) }
            composable("settings") { SettingsScreen(navController, taskViewModel, settingsStore) }
        }
    }
}