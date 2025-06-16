package com.example.taskmate.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskmate.model.TaskEntity

@Database(entities = [TaskEntity::class], version = 2)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
}
