package com.example.taskmate
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.compose.runtime.Composable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.taskmate.data.SettingsDataStore
import com.example.taskmate.navigation.NavGraph
import com.example.taskmate.ui.theme.TaskMateTheme
import com.example.taskmate.viewmodel.TaskViewModel
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.taskmate.auth.GoogleAuthUiClient
import com.example.taskmate.data.TaskDatabase
import com.example.taskmate.viewmodel.TaskViewModelFactory
import com.google.android.gms.auth.api.identity.Identity
import com.example.taskmate.data.TaskDatabase
import com.example.taskmate.viewmodel.TaskViewModelFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val googleAuthUiClient = GoogleAuthUiClient(
                context = applicationContext,
                oneTapClient = Identity.getSignInClient(applicationContext)
            )

            val context = LocalContext.current
            val settingsStore = remember { SettingsDataStore(context) }
            val darkModeEnabled by settingsStore.darkModeFlow.collectAsState(initial = false)
            val db = Room.databaseBuilder(
                applicationContext,
                TaskDatabase::class.java,
                "tasks-db"
            ).build()
            val taskDao = db.taskDao()

            val factory = TaskViewModelFactory(taskDao)
            val taskViewModel = ViewModelProvider(this, factory).get(TaskViewModel::class.java)


            TaskMateTheme(darkTheme = darkModeEnabled) {
                NavGraph(taskViewModel = taskViewModel, settingsStore = settingsStore, googleAuthUiClient = googleAuthUiClient)
            }
        }
    }
}