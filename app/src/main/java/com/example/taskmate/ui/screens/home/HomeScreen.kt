package com.example.taskmate.ui.screens.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.taskmate.viewmodel.TaskViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: TaskViewModel = viewModel()) {
    HomeScreenContent(
        taskText = viewModel.taskText,
        onTaskTextChange = viewModel::onTaskTextChange,
        tasks = viewModel.taskList,
        onAddTask = viewModel::addTask,
        onToggleTask = viewModel::toggleTaskDone
    )
}

