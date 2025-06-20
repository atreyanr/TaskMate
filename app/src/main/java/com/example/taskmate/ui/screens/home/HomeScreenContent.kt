package com.example.taskmate.ui.screens.home


//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.setValue
//import com.example.taskmate.data.Task
//import com.example.taskmate.ui.screens.home.TaskInput
//import com.example.taskmate.ui.screens.home.TaskList
//import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskmate.data.Task

@Composable
fun HomeScreenContent(
    tasks: List<TaskEntity>,
    onAddTask: (title: String, category: String) -> Unit,
    onToggleTask: (TaskEntity) -> Unit,
    onClearTasks: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var showClearConfirm by remember { mutableStateOf(false) }
    var dontAskAgain by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            Row(
                modifier = Modifier.padding(end = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                FloatingActionButton(onClick = { showDialog = true }) {
                    Icon(Icons.Default.Add, contentDescription = "Add Task")
                }
                FloatingActionButton(onClick = {
                    if (dontAskAgain) {
                        onClearTasks()
                    } else {
                        showClearConfirm = true
                    }
                }) {
                    Icon(Icons.Default.DeleteSweep, contentDescription = "Clear All Tasks")
                }
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

        if (showDialog) {
            AddTaskDialog(
                onDismiss = { showDialog = false },
                onAddTask = { title, category ->
                    onAddTask(title, category)
                    showDialog = false
                }
            )
        }

        if (showClearConfirm) {
            AlertDialog(
                onDismissRequest = { showClearConfirm = false },
                title = { Text("Confirm Clear All Tasks") },
                text = {
                    Column {
                        Text("This will delete all tasks permanently. Do you want to continue?")
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = dontAskAgain,
                                onCheckedChange = { dontAskAgain = it }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Don't ask again")
                        }
                    }
                },
                confirmButton = {
                    TextButton(onClick = {
                        onClearTasks()
                        showClearConfirm = false
                    }) {
                        Text("Yes, clear")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showClearConfirm = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}