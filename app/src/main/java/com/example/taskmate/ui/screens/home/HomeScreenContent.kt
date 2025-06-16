package com.example.taskmate.ui.screens.home


//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.setValue
//import com.example.taskmate.data.Task
//import com.example.taskmate.ui.screens.home.TaskInput
//import com.example.taskmate.ui.screens.home.TaskList
//import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskmate.model.TaskEntity
import com.example.taskmate.ui.components.AddTaskDialog


@Composable
fun HomeScreenContent(
    tasks: List<TaskEntity>,
    onAddTask: (title: String, category: String) -> Unit,
    onToggleTask: (TaskEntity) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                showDialog = true
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add Task")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            TaskList(tasks, onToggleTask)
        }

        // Show Dialog
        if (showDialog) {
            AddTaskDialog(
                onDismiss = { showDialog = false },
                onAddTask = { title, category ->
                    onAddTask(title, category)
                    showDialog = false
                    }
                )
            }
        }
    }