package com.louisngatale.twitterclone.presentation.ui.home

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.louisngatale.twitterclone.R
import com.louisngatale.twitterclone.repository.authentication.login.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject
constructor(
    application: Application,
) : AndroidViewModel(application) {
    val context: Context = getApplication<Application>().applicationContext!!
    private val getResource: Resources = context.resources
    private val sharedPref: SharedPreferences = context
        .getSharedPreferences(getResource.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
    val loggedIn = mutableStateOf(sharedPref.getString("loggedIn","false"))


    fun logout(){
        with (sharedPref.edit()) {
            putString("user_id", "")
            putString("username", "")
            putString("email", "")
            putString("first_name", "")
            putString("second_name", "")
            putString("profile_picture", "")
            putString("token", "")
            // TODO: Login to null
            putString("loggedIn", "false")
            apply()
        }
    }
}