package com.example.taskmate.ui.screens.home

//import com.example.taskmate.data.Task
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskmate.model.TaskEntity

@Composable
fun TaskItem(task: TaskEntity, onToggle: (TaskEntity) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(task.title)
            Checkbox(
                checked = task.isDone,
                onCheckedChange = { onToggle(task) }
            )
        }
    }
}