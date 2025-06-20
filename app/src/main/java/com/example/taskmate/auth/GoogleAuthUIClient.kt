package com.example.taskmate.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.IntentSenderRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.tasks.await

data class SignInResult(
    val data: SignInCredential?,
    val errorMessage: String?
)

class GoogleAuthUiClient(
    private val context: Context,
    private val oneTapClient: SignInClient
) {
    private val signInRequest = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId("14224764114-1r2gmfknhgop81oebmjj7bfc3l719d5f.apps.googleusercontent.com") // replace this!
                .setFilterByAuthorizedAccounts(false)
                .build()
        )
        .setAutoSelectEnabled(true)
        .build()

    suspend fun signIn(): IntentSenderRequest {
        val result = oneTapClient.beginSignIn(signInRequest).await()
        return IntentSenderRequest.Builder(result.pendingIntent.intentSender).build()
    }

    suspend fun signInWithIntent(intent: Intent): SignInResult {
        return try {
            val credential = oneTapClient.getSignInCredentialFromIntent(intent)
            SignInResult(data = credential, errorMessage = null)
        } catch (e: ApiException) {
            SignInResult(data = null, errorMessage = e.message)
        }
    }

    fun signOut() {
        oneTapClient.signOut()
    }
}
