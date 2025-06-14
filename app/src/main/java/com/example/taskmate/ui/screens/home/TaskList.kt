package com.example.taskmate.ui.screens.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.taskmate.data.Task

@Composable
fun TaskList(tasks: List<Task>, onToggle: (Task) -> Unit) {
    LazyColumn {
        items(tasks) { task ->
            TaskItem(task, onToggle)
        }
    }
}