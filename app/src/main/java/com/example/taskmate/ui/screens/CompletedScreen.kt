package com.example.taskmate.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskmate.viewmodel.TaskViewModel
import com.example.taskmate.ui.screens.home.TaskItem
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import com.example.taskmate.model.TaskEntity


@Composable
fun CompletedScreen(viewModel: TaskViewModel) {
    val allTasks by viewModel.taskList.collectAsState()

    val completedTasks: List<TaskEntity> = allTasks.filter { it.isDone }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Completed Tasks",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (completedTasks.isEmpty()) {
            Text("No tasks completed yet.")
        } else {
            LazyColumn {
                items(completedTasks) { task ->
                    TaskItem(task = task, onToggle = { viewModel.toggleTaskDone(task) })
                }
            }
        }
    }
}