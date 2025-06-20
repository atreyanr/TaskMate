package com.example.taskmate.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow

val Context.dataStore by preferencesDataStore("settings")

class SettingsDataStore(private val context: Context) {

    companion object {
        val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")
        val PROFILE_IMAGE_URL_KEY = stringPreferencesKey("profile_image_url")
        val USER_DETAILS_KEY = stringPreferencesKey("user_details")
        val SIGNED_IN_EMAIL_KEY = stringPreferencesKey("signed_in_email")
    }

    val darkModeFlow: Flow<Boolean> = context.dataStore.data
        .map { prefs -> prefs[DARK_MODE_KEY] ?: false }

    val profileImageUrlFlow: Flow<String?> = context.dataStore.data
        .map { prefs -> prefs[PROFILE_IMAGE_URL_KEY] }

    val userDetailsFlow: Flow<String?> = context.dataStore.data
        .map { prefs -> prefs[USER_DETAILS_KEY] }

    val signedInEmailFlow: Flow<String?> = context.dataStore.data
        .map { prefs -> prefs[SIGNED_IN_EMAIL_KEY] }

    suspend fun setDarkMode(enabled: Boolean) {
        context.dataStore.edit { settings ->
            settings[DARK_MODE_KEY] = enabled
        }
    }

    suspend fun setProfileImageUrl(url: String?) {
        context.dataStore.edit { settings ->
            if (url != null) settings[PROFILE_IMAGE_URL_KEY] = url else settings.remove(PROFILE_IMAGE_URL_KEY)
        }
    }

    suspend fun setUserDetails(detailsJson: String) {
        context.dataStore.edit { settings ->
            settings[USER_DETAILS_KEY] = detailsJson
        }
    }

    suspend fun setSignedInEmail(email: String?) {
        context.dataStore.edit { settings ->
            if (email != null) settings[SIGNED_IN_EMAIL_KEY] = email else settings.remove(SIGNED_IN_EMAIL_KEY)
        }
    }
}