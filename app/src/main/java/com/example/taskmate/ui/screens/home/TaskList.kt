package com.example.taskmate.ui.screens.home

//import com.example.taskmate.data.Task
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.taskmate.model.TaskEntity

@Composable
fun TaskList(tasks: List<TaskEntity>, onToggle: (TaskEntity) -> Unit) {
    LazyColumn {
        items(tasks) { task ->
            TaskItem(task, onToggle)
        }
    }
}