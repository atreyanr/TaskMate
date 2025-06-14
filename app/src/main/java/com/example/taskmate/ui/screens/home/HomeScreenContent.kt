package com.example.taskmate.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskmate.data.Task

@Composable
fun HomeScreenContent(
    taskText: String,
    onTaskTextChange: (String) -> Unit,
    tasks: List<Task>,
    onAddTask: () -> Unit,
    onToggleTask: (Task) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddTask) {
                Icon(Icons.Default.Add, contentDescription = "Add Task")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            TaskInput(taskText, onTaskTextChange)
            Spacer(modifier = Modifier.height(16.dp))
            TaskList(tasks, onToggleTask)
        }
    }
}
