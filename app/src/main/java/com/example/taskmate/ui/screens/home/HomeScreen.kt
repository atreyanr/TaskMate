package com.example.taskmate.ui.screens.home

//import androidx.compose.material3.Text
//import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.taskmate.viewmodel.TaskViewModel
import androidx.compose.runtime.getValue



@Composable
fun HomeScreen(viewModel: TaskViewModel = viewModel()) {
    val taskList by viewModel.taskList.collectAsState()

    HomeScreenContent(
        tasks = taskList,
        onAddTask = viewModel::addTask, // Plug in real input
        onToggleTask = viewModel::toggleTaskDone,
        onClearTasks = viewModel::clearTasks
    )
}

