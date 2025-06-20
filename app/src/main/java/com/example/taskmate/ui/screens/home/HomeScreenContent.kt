package com.example.taskmate.ui.screens

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmate.auth.GoogleAuthUiClient
import com.example.taskmate.data.SettingsDataStore
import com.example.taskmate.viewmodel.TaskViewModel
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: TaskViewModel,
    settingsStore: SettingsDataStore,
    googleAuthUiClient: GoogleAuthUiClient
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val darkMode by settingsStore.darkModeFlow.collectAsState(initial = false)

    var signedInUserEmail by remember { mutableStateOf("") }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            scope.launch {
                val intent = result.data
                if (intent != null) {
                    val signInResult = googleAuthUiClient.signInWithIntent(intent)
                    signedInUserEmail = signInResult.data?.googleIdToken ?: ""
                } else {
                    Log.e("SignIn", "Intent was null")
                }
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Settings", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        // Sign In Button
        Button(onClick = {
            scope.launch {
                val signInIntentSender = googleAuthUiClient.signIn()
                launcher.launch(signInIntentSender)
            }
        }) {
            Text("Sign in with Google")
        }

        if (signedInUserEmail.isNotEmpty()) {
            Text("Signed in as: $signedInUserEmail")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Dark Mode")
            Switch(
                checked = darkMode,
                onCheckedChange = {
                    scope.launch { settingsStore.setDarkMode(it) }
                }
            )
        }
    }
}