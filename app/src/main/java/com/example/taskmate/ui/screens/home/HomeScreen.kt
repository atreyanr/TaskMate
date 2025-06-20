package com.example.taskmate.ui.screens.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.taskmate.viewmodel.TaskViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: TaskViewModel = viewModel()) {
    HomeScreenContent(
        tasks = taskList,
        onAddTask = viewModel::addTask, // Plug in real input
        onToggleTask = viewModel::toggleTaskDone,
        onClearTasks = viewModel::clearTasks
    )
}

