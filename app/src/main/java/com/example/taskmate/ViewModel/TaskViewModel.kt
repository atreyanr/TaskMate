package com.example.taskmate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmate.data.TaskDao
import com.example.taskmate.model.TaskEntity
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.launch

class TaskViewModel(private val taskDao: TaskDao) : ViewModel() {

    val taskList = taskDao.getAllTasks()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addTask(title: String, category: String) {
        viewModelScope.launch {
            val newTask = TaskEntity(title = title, category = category)
            taskDao.insert(newTask)
        }
    }

    fun toggleTaskDone(task: TaskEntity) {
        viewModelScope.launch {
            val updated = task.copy(isDone = !task.isDone)
            taskDao.insert(updated)
        }
    }

    fun clearTasks() {
        viewModelScope.launch {
            taskList.value.forEach { taskDao.delete(it) }
        }
    }
}
