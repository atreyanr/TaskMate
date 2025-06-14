package com.example.taskmate.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.taskmate.data.Task

class TaskViewModel : ViewModel() {

    var taskText by mutableStateOf("")
        private set

    var taskList = mutableStateListOf<Task>()
        private set

    fun onTaskTextChange(newText: String) {
        taskText = newText
    }

    fun addTask() {
        if (taskText.isNotBlank()) {
            taskList.add(Task(taskList.size + 1, taskText))
            taskText = ""
        }
    }

    fun clearTasks() {
        taskList.clear()
    }


    fun toggleTaskDone(task: Task) {
        val index = taskList.indexOf(task)
        if (index != -1) {
            taskList[index] = task.copy(isDone = !task.isDone)
        }
    }
}
