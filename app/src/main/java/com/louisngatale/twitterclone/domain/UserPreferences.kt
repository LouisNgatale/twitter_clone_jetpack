package com.louisngatale.twitterclone.domain

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.louisngatale.twitterclone.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences (
    context: Context
) {
    private val applicationContext = context.applicationContext

    val authToken: Flow<String?>
        get() = applicationContext.datastore.data.map { preferences ->
            preferences[KEY_AUTH]
        }

    val user : Flow<User?>
        get() = applicationContext.datastore.data.map { preferences ->
            User(preferences[KEY_AUTH] ?: "",
                preferences[CREATED_AT]?: "",
                preferences[DOB]?: "",
                preferences[EMAIL]?: "",
                preferences[EMAIL_VERIFIED_AT]?: "",
                (preferences[USER_ID]?: "") as Int,
                preferences[NAME]?: "",
                (preferences[PHONE_NUMBER]?: "") as Int,
                preferences[PROFILE_IMAGE]?: "",
                preferences[UPDATED_AT]?: "",
                preferences[USERNAME]?: "")
        }

    suspend fun saveAuthToken(authToken : String){
        applicationContext.datastore.edit { preferences ->
            preferences[KEY_AUTH] = authToken
        }
    }

    suspend fun revokeToken(){
        applicationContext.datastore.edit { preferences ->
            preferences[KEY_AUTH] = ""
        }
    }

    companion object{
        private val Context.datastore: DataStore<Preferences> by preferencesDataStore("session")
        private val KEY_AUTH = stringPreferencesKey("access_token")
        private val USER_ID = intPreferencesKey("user_id")
        private val CREATED_AT = stringPreferencesKey("create_at")
        private val DOB = stringPreferencesKey("dob")
        private val EMAIL = stringPreferencesKey("email")
        private val EMAIL_VERIFIED_AT = stringPreferencesKey("email_verified_at")
        private val NAME = stringPreferencesKey("name")
        private val PHONE_NUMBER = intPreferencesKey("phone_number")
        private val PROFILE_IMAGE = stringPreferencesKey("profile_image")
        private val UPDATED_AT = stringPreferencesKey("updated_at")
        private val USERNAME = stringPreferencesKey("username")
    }
}